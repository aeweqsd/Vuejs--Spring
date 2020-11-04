<template>
    <v-container style="max-width:500px">
        <v-layout>
            <v-flex xs12
            style="z-index:100">
                <v-alert class="elevation-12">
                    <v-toolbar
                    color="primary"
                    dark
                    flat>
                    <v-toolbar-title>
                        Logout
                    </v-toolbar-title>
                    <v-spacer></v-spacer>
                    </v-toolbar>
                    <v-card-text>
                        정말 로그아웃하시겠습니까?
                    </v-card-text>
                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" @click="logout">예</v-btn>
                    <v-btn color="#ECEFF1" @click="cancel">아니요</v-btn>
                    </v-card-actions>
                </v-alert>
            </v-flex>
        </v-layout>
    </v-container>
</template>
<script>
import {mapAction,mapState} from "vuex"
export default {
    methods:{
        logout(payload){
            this.$store.dispatch('logoutProcess',payload)
        },
        cancel(){
           this.$router.push('/boardlist')
        }

    }
}
</script>