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
    boardpage:0,
    maxpage:0,
    board_detail:[],
    boardSubject:[],
    commentList:[],
    searchlist:[],
    menu:[
      { icon: 'mdi-login',
        text:'로그인',
        link:'/login'
      }
    ],
    Keyword:[],
    test:[],
    search:'',
    note:[]
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
    state.boardList = data
    Route.push("/boardlist")
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
  },
  SET_COMMENTLIST(state,data){
    state.commentList=data
  },
  SET_COMMENTDELETE(state,data){
    var index = state.commentList.findIndex(i=> i.idcomment == data);
    state.commentList.splice(index,1)
  },
  SET_PAGE(state,data){
    state.boardpage =data
  },
  SET_MAXPAGE(state,data){
    state.maxpage=data
  },
  SET_BOARDPAGE(state,data){
    state.boardpage=data
  },
  SET_KEYWORD(state,data){
    state.Keyword=data
  },
  SET_TEST(state,data){
    state.test=data
  },
  SET_SEARCHLIST(state,data){
    state.searchlist=data
  },
  SET_SEARCH(state,data){
    state.Search=data
  },
  SET_NOTE(state,data){
    state.note=data
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
      
            },
            { 
              icon :'mdi-forum',
              text :'쪽지',
              link :'/note'
            },
            {   icon : 'mdi-cog-outline',
                text : 'Setting',
                link : '/Setting'

            }    
          
          ])
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
  
        },
        { 
          icon :'mdi-forum',
          text :'쪽지',
          link :'/Note'
        },
        { 
          icon : 'mdi-cog-outline',
          text : 'Setting',
          link : '/Setting'    
      }
      ])
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
    boardlist({commit},payload){
      console.log(payload)
      return new Promise((resolve,reject) =>{
        axios.get('http://localhost:9000/api/test/user',{
          params:{
            boardpage:payload
          }
        })
        .then(Response=>{
          console.log(Response.data)
          commit('SET_MAXPAGE' ,Response.data[0].boardmax)
          commit('SET_BOARDPAGE',payload)
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
    },
    CommentWrite({commit},payload){
      console.log(payload)
      return new Promise((resolve,reject) =>{
        axios.post('http://localhost:9000/api/test/comment',payload)
        .then(Response=>{
          console.log(Response.data)
          commit('SET_COMMENTLIST',Response.data)
        })
      })
    }, 
    selectcomment({commit},payload){
      console.log(payload)
      return new Promise((resolve,reject) =>{
        axios.get('http://localhost:9000/api/test/comment',{
          params:{
            idboard : payload
          }
        })
        .then(Response=>{
          console.log(Response.data)
          commit('SET_COMMENTLIST',Response.data)
        })
        .catch(Error=>{
          console.log('error')
          reject(Error)
        })
      })
    },
    CommentDelete({commit},payload){
      console.log(payload)
      return new Promise((resolve,reject) =>{
        axios.delete('http://localhost:9000/api/test/comment/'+payload.idcomment)
        .then(Response=>{
          console.log(Response.data)
          commit('SET_COMMENTDELETE',Response.data)
        })
        .catch(Error=>{
          console.log('error')
          reject(Error)
        })
      })
    },
    BoardCreate({commit},payload){
      console.log(payload)
      return new Promise((resolve,reject) =>{
        axios.post('http://localhost:9000/api/test/board',payload)
      .then(Response=>{
        console.log(Response.data)
        router.push('/boardlist')
      })
      .catch(Error=>{
        console.log('error')
        reject(Error)
      })
    })
    },
    InitKeyword({commit},payload){
      return new Promise((resolve,reject) =>{
        axios.get('http://localhost:9000/api/test/keyword')
        .then(Response=>{
  //    let a = new Map;
  //        Object.keys(Response.data).forEach(k=>{a.set(k,Response.data[k])})
  //        console.log(a)
  //        commit('SET_TEST',a)
          commit('SET_KEYWORD',Object.entries(Response.data))
        })

      })
    },
    ChangeKeyword({commit},payload){
      console.log(payload)
      commit('SET_KEYWORD',payload)
    },
    InputKeyword({commit,state},payload){
 //      console.log(state.Keyword)
 //     console.log(JSON.stringify(state.Keyword))
 //       console.log(Object.fromEntries(state.test))
      return new Promise((resolve,reject)=>{
        axios.post('http://localhost:9000/api/test/keyword',Object.fromEntries(state.Keyword))
        .then(Response=>{
          console.log(Response.data)
        })
        commit('SET_KEYWORD',"")
      })
     
    },
    Search_List({commit},payload){
      console.log(payload)
       axios.get('http://localhost:9000/api/test/search',{
         params:{
           keyword : payload
         }
       })
       .then(Response=>{
       console.log(Response.data)
        if(Response.data.length >3){
         Response.data = Response.data.slice(0,3)
        }
        commit('SET_SEARCHLIST',Response.data)
       })
    },
    Search({commit},payload){
      axios.get('http://localhost:9000/api/test/searchlist',{
        params:{
          keyword:payload
        }
      })
      .then(Response=>{
        console.log(Response.data)

        commit('SET_BOARDLIST',Response.data)
        commit('SET_MAXPAGE' ,Response.data[0].boardmax)
        commit('SET_SEARCHLIST',[])
        commit('SET_SEARCH',payload)
      })
    },
    sendnote({commit},payload){
      console.log(payload)
      axios.post('http://localhost:9000/api/test/note',payload)
       .then(Response=>{
        console.log(Response.date)
      })
    },
    selectNote({commit},payload){
      console.log(payload)
      axios.get('http://localhost:9000/api/test/note',{
        params:{
          id:payload
        }
      }).then(Response=>{
        commit('SET_NOTE',Response.data)
        console.log(Response.data)
      })
    },
    noteread({commit},payload){
      console.log(payload)
      axios.patch('http://localhost:9000/api/test/note',payload)
      .then(Response=>{
        console.log(Response.data)
      })
    }
  }
})
