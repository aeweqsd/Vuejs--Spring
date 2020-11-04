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
                        @change="abc()"
                        ></v-select>
                        <v-combobox
                        v-if = "text"
                        v-model='othersub'
                        :search-input.sync="sub"
                        multiple
                        persistent-hint
                        small-chips
                        clearable
                        label="글 주제를 만들어주세요"
                        ></v-combobox>
                        
                        <v-textarea
                        label="글내용"
                        name="content"
                        v-model="content"
                        clearable
                        prepend-icon="mdi-comment"
                        ></v-textarea>
                    </v-form>
                    <v-card-actions></v-card-actions>
                    <v-btn @click="BoardCreate({title:title,content:content,userid:userid,subject:subject,othersub:othersub})">글생성</v-btn>
                    <v-btn @click="cancel()" color="lime lighten-3">생성취소</v-btn>
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
            item : this.$store.state.boardSubject,
            text:false,
            othersub:[],
            sub:null,
            title:'',
            content:''
        }

    },
    watch:{
        othersub(val){
            if(val.length >1){
                this.$nextTick(() => this.othersub.pop())
            }
        }
    },
    methods:{
        ...mapActions(['BoardCreate','boardList','boardsubject']),
        aa:function(){
            let a= new Array();
            this.item.forEach(el=> a.push(el.subjectname))
            a.push("기타")
            return a.slice(1,a.length)    //ALL ->주제에서 배제
        },
        abc:function(){
            console.log(this.subject)
            if(this.subject =='기타'){
                this.text = true 
                return
            }else{
                this.text=false
                return
            }
        },
        cancel:function(){
            this.$router.push('/boardlist')
        }
    }
    
}
</script>

<style>

</style>