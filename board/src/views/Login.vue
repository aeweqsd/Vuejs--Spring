<template>
<v-container style="max-width:500px">
    <v-layout>
        <v-flex xs12
        style="z-index:100"
        >
            <v-alert type="error"
                class="mb-3"
                :value="login_err">
                아이디 /비밀번호를 확인해주세요
            </v-alert>
            <v-alert class="mb-3"
            type="success"
            :value="login_success">
              로그인 성공
            </v-alert>    
            <v-card class="elevation-12">
                <v-toolbar
                color="primary"
                dark
                flat
                >
                <v-toolbar-title>
                    Login
                </v-toolbar-title>
                <v-spacer></v-spacer>
                </v-toolbar>
                <v-card-text>
                <v-form>
                    <v-text-field
                    label="ID"
                    name="ID"
                    v-model="username"
                    prepend-icon="mdi-account"
                    type="text"
                    ></v-text-field>

                    <v-text-field
                    id="password"
                    label="Password"
                    v-model="password"
                    name="password"
                    prepend-icon="mdi-lock"
                    type="password"
                    ></v-text-field>
                </v-form>
                </v-card-text>
                <v-card-actions>
                <v-spacer></v-spacer>
                 <v-btn color="primary" @click="login({username:username, password:password})">Login</v-btn>


                <v-btn color="#ECEFF1" router :to="{name:'SignUp'}">Sign Up</v-btn>
                </v-card-actions>
            </v-card>
        </v-flex>
    </v-layout>
</v-container>
</template>

<script>
import {mapAction,mapState} from "vuex"
export default{
    data(){
        return{
            username:null,
            password:null,
        }
    },
    methods:{
        login(payload){
            this.$store.dispatch('loginProcess',payload)
        }
    },
    computed:{
        ...mapState(["login_err","login_success"])
    }

}
</script>