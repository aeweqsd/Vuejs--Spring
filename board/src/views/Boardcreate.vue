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
                        <v-select
                        :items="aa()"
                        label="주제"
                        prepend-icon="mdi-animation"
                        v-model="subject"
                        ></v-select>
                        <v-textarea
                        label="글내용"
                        name="content"
                        v-model="content"
                        clearable
                        prepend-icon="mdi-comment"
                        ></v-textarea>
                    </v-form>
                    <v-card-actions></v-card-actions>
                    <v-btn @click="BoardCreate({title:title,content:content,userid:userid,subject:subject})">글생성</v-btn>
                    <v-btn @click="reverse()" color="lime lighten-3">생성취소</v-btn>
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
            userid  : this.$store.state.Userinfo.User_Id,
            boardpage : this.$store.state.boardpage,
            item : this.$store.state.boardSubject
        }

    },
    computed:{
    },
    methods:{
        ...mapActions(['BoardCreate','boardList','boardsubject']),
        reverse : function(){
            this.$store.dispatch("boardlist",this.boardpage)
        },
        aa:function(){
            let a= new Array();
            this.item.forEach(el=> a.push(el.subjectname))
            return a
        }
    }
    
}
</script>

<style>

</style>