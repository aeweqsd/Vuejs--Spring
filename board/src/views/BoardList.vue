<template>
  <v-container>
    <v-flex xs12>
      <v-flex xs9>
        <v-tabs>
          <template v-for="item in boardSubject"
          >
            <v-tab
            :key="item.idsubject"
            @change="changesub(item.subjectname)">{{item.subjectname}}</v-tab>
          </template>
        </v-tabs>
        <template v-for="(item) in boardList">
        <v-card class="pa-3" outlined
        :key ="item.idboard"
        style="z-index=2">
          <v-list three-line>
             <v-list-item
                
                @click="boardDetail(item.idboard)"
              >
              <v-list-item-content>
                <v-list-item-subtitle v-html="item.boardname"></v-list-item-subtitle>
                <v-list-item-subtitle v-html="item.boardwriter"></v-list-item-subtitle>
                <v-list-item-subtitle v-html="time(item.timediff)"></v-list-item-subtitle>
                <v-list-item-subtitle v-html="item.hit"></v-list-item-subtitle>
                <v-list-item-subtitle>{{item.boardsubject}}</v-list-item-subtitle>
              </v-list-item-content>
              </v-list-item>
          </v-list>
        </v-card>
        </template>
        <p class="text-sm-center">
          <v-pagination
          v-model="boardpage"
          :length="page234(maxpage)"
          :total-visible="5"
          @input="next(boardpage)"
          >
          </v-pagination>
        </p>
        <v-card class="d-flex justify-center align-center order-6"
        elevation="0">
        </v-card>
        <v-card 
        class="d-flex justify-center align-end sm-4"
        elevation="0">
        </v-card>
        <p class="text-sm-right">
        <v-btn @click ="reset">검색어 초기화</v-btn>
        <v-btn @click="goBoardcreate">글생성</v-btn></p>
      </v-flex>
    </v-flex>
  </v-container>
</template>
<script>

import {mapState, mapActions} from "vuex"
import sample from '../components/BoardSubject.vue'


export default {
  created(){
    if(this.$store.state.boardList == null){
      this.$store.dispatch('boardlist',[1,this.$store.state.subject])
    }
    this.$store.dispatch('boardsubject')
    
  },

  data(){
    return{
      boardpage : this.$store.state.boardpage
    }
  }
  ,

  computed:{
    ...mapState(['boardList','maxpage','boardpage','boardSubject','subject'])
  },
  methods:{
    ...mapActions(['boardDetail','boardWrite','boardlist','boardsubject']),
    page234:function(a){
        if(a%5 ==0){
          return Math.floor(a/5);
        } else{
          return Math.floor(a/5)+1;
        }

    },
    time: function(a){
      var date = a.split(':');
        if(date[0] !=0){
            if(date[0]/24 >=1){
           //     console.log(Math.round(date[0]/24)+"일 전")
                return Math.round(date[0]/24)+"일 전";
            }else{
           //     console.log(date[0] +"시간 전")
                return date[0] +"시간 전";
            }
        }else if(date[1] !=0){
        //  console.log(date[1]+"분 전")
          return date[1]+"분 전";
        }else{
        //  console.log(date[2]+"초 전")
          return date[2]+"초 전";
} 
    
    },
    goBoardcreate: function(){
      this.$router.push('/boardcreate')
    },
    next:function(a){
      console.log(a)
      this.$store.dispatch("boardlist",[a,this.$store.state.subject])
    },
    reset:function(){
      this.$store.dispatch('boardlist',[1,'ALL'])
    },
    aaa(){
      console.log(" ");
    },
    changesub(a){
        this.$store.dispatch("boardlist",[1,a])
      
    }
  
  },
  
  components:{
      sample
  }
}
</script>