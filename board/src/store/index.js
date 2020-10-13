import Vue from 'vue'
import Vuex from 'vuex'
import Route from '../router/index'
import axios from 'axios'
import router from '../router/index'
import cookie from 'vue-cookies'
Vue.use(Vuex)

const resourceHost ="http://localhost:8080"

export default new Vuex.Store({
  state: {
    Userinfo:{User_Id:null,User_Name:null,User_auth:[]},
    login_err:false,
    login_success:false,
    boardList:[],
    board_detail:[],
    boardSubject:[],
    menu:[
      { icon: 'mdi-login',
        text:'로그인',
        link:'/login'
      }
    ]
  },
  getters:{
    allUsers: state=>{
      return state.UserList.length
    }

  },
  
  mutations: {
    NewUsers:(state,payload) =>{
      state.UserList.push(payload)
      Route.push("/login")
    },
    SET_USER(state,data){
      state.Userinfo.User_Id = data.username
      state.Userinfo.User_Name =data.name
      state.Userinfo.User_auth=data.authorities
      Route.push("/user")
  },
  SET_BOARDLIST(state,data){
    state.boardlist = data
  },
  SET_BOARDDETAIL(state,data){
    state.board_detail=data
    Route.push("/boardDetail/"+data.idboard)
  },
  SET_BOARDDELETE(state,data){
    console.log(data)
    var index = state.boardList.findIndex(i => i.idboard == data);
    console.log(index);
    state.boardList.splice(index,1);
    Route.push("/boardlist")
  },
  SET_BOARDSUBJECT(state,data){
    state.boardSubject=data
  },
  SET_MENU(state,data){
    state.menu = data
  }
},
  actions: {
    loginProcess({commit},payload){
      console.log(payload)
      return new Promise((resolve,reject) =>{
        axios.post('http://localhost:9000/api/auth/signin',payload)
        .then(Response =>{
          console.log(Response.data)
          cookie.set("User",Response.data)
          if(Response.data.username != null){
            axios.defaults.headers.common['Authorization'] = `Bearer ${Response.data.token}`
            commit('SET_MENU', [{
              icon:'mdi-logout',
              text:'로그아웃',
              link:'/Logout'
            },
            {
              icon:'mdi-message-text',
              text:'게시판',
              link:'/boardlist'
      
            }])
            commit('SET_USER',Response.data) 
          }
        })
        .catch(Error=>{
          console.log('error')
          reject(Error)
        })

      })

    },
    islogin({commit},payload){
      if(cookie.isKey("User")){
        var info = cookie.get("User")
        axios.defaults.headers.common['Authorization'] = `Bearer ${info.token}`
        commit('SET_MENU', [{
          icon:'mdi-logout',
          text:'로그아웃',
          link:'/Logout'
        },
        {
          icon:'mdi-message-text',
          text:'게시판',
          link:'/boardlist'
  
        }])
        commit('SET_USER',info) 
        
      console.log(info)
      }

    },
    logoutProcess({commit},payload){
      console.log(sessionStorage);
      sessionStorage.clear();
      cookie.remove("User");
      axios.defaults.headers.common['Authorization'] ='';
      commit('SET_MENU', [{
        icon: 'mdi-login',
        text:'로그인',
        link:'/login'
      }]
     )
      router.push("/")
    },
    SignUp({commit},payload){
      console.log(payload)
      return new Promise((resolve,reject) =>{
        axios.post('http://localhost:9000/api/auth/signup',payload)
        .then(Response =>{
          console.log(Response.data)
          if(Response.data === "success"){
            Route.push("/login")
          }
        })
        .catch(Error=>{
          console.log('error')
          reject(Error)
        })
      })
    },
    boardList({commit}){
      return new Promise((resolve,reject) =>{
        axios.get('http://localhost:9000/api/test/user')
        .then(Response=>{
          console.log(Response.data)
          commit('SET_BOARDLIST',Response.data)
        })
        .catch(Error=>{
          console.log('error')
          reject(Error)
        })
      })

    },
    boardDetail({commit},payload){
      console.log(payload)
      return new Promise((resolve,reject) =>{
        axios.get('http://localhost:9000/api/test/board',{
          params:{
            idboard : payload
          }
        })
        .then(Response=>{
          console.log(Response.data)
          commit('SET_BOARDDETAIL',Response.data)
        })
        .catch(Error=>{
          console.log('error')
          reject(Error)
        })
      })
    },
    boardSubject({commit},payload){
      return new Promise((resolve,reject) =>{
       axios.get('http://localhost:9000/api/test/boardSubject',{
       })
       .then(Response=>{
         console.log(Response.data)
         commit('SET_BOARDSUBJECT',Response.data)
       })
       .catch(Error=>{
         console.log('Error')
         reject(Error)
       })
      })

    },
    NewUsers:({commit},payload) =>{
      commit('NewUsers',payload)
    },
    BoardDelete({commit},payload){
      console.log(payload)
      return new Promise((resolve,reject) =>{
          axios.delete('http://localhost:9000/api/test/board/'+payload.idboard)
          .then(Response =>{
            console.log(Response.data)
            commit('SET_BOARDDELETE',Response.data)
          })
          .catch(Error =>{
            console.log('error')
            reject(Error)
          })
      })
    },
    Subjectselect({commit},payload){
      console.log(payload)
      return new Promise((resolve,reject) =>{
        axios.post('http://localhost:9000/api/test/subjectselect',{
          name:payload.boardSubject
        })
        .then(Response =>{
          console.log(Response.data)
        
        })
      })
    }  
  }
})
