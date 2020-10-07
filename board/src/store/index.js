import Vue from 'vue'
import Vuex from 'vuex'
import Route from '../router/index'
import axios from 'axios'
Vue.use(Vuex)
const resourceHost ="http://localhost:8080"

export default new Vuex.Store({
  state: {
    Userinfo:{User_Id:null,User_Name:null,User_auth:[]},
    login_err:false,
    login_success:false,
    boardList:[],
    board_detail:[],
    boardSubject:[]
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
    Route.push("/boardDetail/" + data.idboard)
  },
  SET_BOARDDELETE(state,data){
    var index = state.boardList.findIndex(i => i.idboard == data);
    state.boardList.splice(index,1);
    Route.push("/boardlist")
  },
  SET_BOARDSUBJECT(state,data){
    state.boardSubject=data

  }
},
  actions: {
    loginProcess({commit},payload){
      console.log(payload)
      return new Promise((resolve,reject) =>{
        axios.post('http://localhost:9000/api/auth/signin',payload)
        .then(Response =>{
          console.log(Response.data)
          if(Response.data.username != null){
            axios.defaults.headers.common['Authorization'] = `Bearer ${Response.data.token}`
            commit('SET_USER',Response.data) 
          }
        })
        .catch(Error=>{
          console.log('error')
          reject(Error)
        })
        

      })

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
        axios.get('http://localhost:9000/api/test/boardDetail',{
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
    }
  }
})
