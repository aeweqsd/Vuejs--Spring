<template>
  <v-container>
      <v-flex xs12>
          <v-card class="elevation-12" outlined>
              <v-toolbar
              color="#651FFF"
              dark
              flat>
              <v-toolbar-title>
                  글생성
              </v-toolbar-title>
              <v-spacer></v-spacer>
              </v-toolbar>
                <v-card-text>
                    <v-form>
                        <v-text-field
                        label="글제목"
                        name="title"
                        counter="50"
                        v-model="title"
                        clearable
                        prepend-icon="mdi-billboard"
                        type="text"
                        required
                        >
                        </v-text-field>
                        <v-textarea
                        label="글내용"
                        name="content"
                        v-model="content"
                        clearable
                        prepend-icon="mdi-comment"
                        ></v-textarea>
                    </v-form>
                    <v-card-actions></v-card-actions>
                    <v-btn @click="BoardCreate({title:title,content:content,userid:userid})">글생성</v-btn>
                    <v-btn @click="reverse()" color="#E53935">생성취소</v-btn>
                </v-card-text>
          </v-card>
      </v-flex>
  </v-container>
</template>

<script>
import {mapActions,mapState} from 'vuex'
export default {
    data(){
        return {
            userid  : this.$store.state.Userinfo.User_Id
        }

    },
    methods:{
        ...mapActions(['BoardCreate']),
        reverse : function(){
        }
    }
    
}
</script>

<style>

</style>