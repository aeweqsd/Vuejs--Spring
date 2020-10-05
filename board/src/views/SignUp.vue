<template>
    <v-container style="max-width:500px">
        <v-layout>
            <v-flex xs12>
                <v-card class="elevation-12">
                    <v-toolbar
                    color="#FBC02D"
                    dark
                    flat
                    >
                    <v-toolbar-title>
                        Sign-up
                    </v-toolbar-title>
                    <v-spacer></v-spacer>
                    </v-toolbar>
                    <v-card-text>
                    <v-form>
                        <v-text-field
                        label="id"
                        name="UserId"
                        v-model="username"
                        prepend-icon="mdi-account"
                        type="text"
                        ></v-text-field>

                        <v-text-field
                        id="Userpassword"
                        label="password"
                        name="Userpassword"
                        v-model="password"
                        prepend-icon="mdi-lock"
                        type="password"
                        ></v-text-field>
                        <v-text-field
                        id="name"
                        label="Username"
                        v-model="name"
                        prepend-icon="mdi-card-account-details"
                        type="text"
                        >
                        </v-text-field>
                        <v-text-field
                        id="UserPhone"
                        label="PhoneNumber"
                        name="UserPhone"
                        v-model="phone"
                        prepend-icon="mdi-phone"
                        type="number"
                        ></v-text-field>
                    </v-form>
                    </v-card-text>
                     <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#FBC02D" @click="SignUp({username:username,name:name, password:password, phone:phone})">Sign up</v-btn>
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    data() {
        return {
            username:null,
            password:null,
            name:null,
            phone:null
        }

    },
   methods:{
      ...mapActions(['SignUp']),
/*
       SignUp(){
           let Users ={
               UserId:this.UserId,
               Userpassword:this.Userpassword,
               UserPhone:this.UserPhone

           }
            console.log(Users)
            this.NewUsers(Users)
       }
       */
   }
}
</script>