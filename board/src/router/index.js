import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import BoardList from '../views/BoardList.vue'
import Signup from '../views/SignUp.vue'
import boardDetail from '../views/boardDetail.vue'
import boardSubject from '../views/boardSubject.vue'
import logout from "../views/Logout.vue"
import boardcreate from "../views/Boardcreate.vue"
import Setting from "../views/Setting.vue"


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path :'/login',
    name: 'Login',
    component : Login
  },
  
  { 
    path:'/boardlist',
    name:'BoardList',
    component : BoardList

  },
  {
    path:'/signup',
    name:'SignUp',
    component : Signup
  },
  {
    path: '/boardDetail/:idboard',
    name: 'boardDetail',
    component: boardDetail
  },
  {
    path: '/boardSubject',
    name: 'boardSubject',
    component : boardSubject
  },
  {
    path:'/Menu',
    name:'menu',
    component: menubar
  },
  {
    path:'/Logout',
    name:'logout',
    component: logout
  },
  {
    path:'/Boardcreate',
    name:'boardcreate',
    component: boardcreate
  },
  {
    path:'/Setting',
    name: 'Setting',
    component : Setting
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
