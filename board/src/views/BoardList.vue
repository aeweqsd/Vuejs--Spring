<template>
  <v-container>
    <v-flex xs12>     
        <template v-for="(item) in boardlist">
        <v-card class="pa-3" outlined
        :key ="item.idboard">
          <v-list three-line>
             <v-list-item
                
                @click="boardDetail(item.idboard)"
              >
              <v-list-item-content>
                <v-list-item-subtitle v-html="item.boardname"></v-list-item-subtitle>
                <v-list-item-subtitle v-html="item.boardwriter"></v-list-item-subtitle>
                <v-list-item-subtitle v-html="time(item.timediff)"></v-list-item-subtitle>
              </v-list-item-content>
              </v-list-item>
          </v-list>
        </v-card>
        </template>
        <p class="text-sm-center">
          <v-pagination
          v-model="boardpage"
          :length="maxpage/5"
          :total-visible="5"
          >
          </v-pagination>
        </p>
        <p class="text-sm-right">
        <v-btn>글생성</v-btn></p>
    </v-flex>
  </v-container>
</template>
<script>

import {mapState, mapActions} from "vuex"
import sample from '../components/BoardSubject.vue'


export default {
  created(){
    this.$store.dispatch("boardList",this.$store.state.boardpage)
  },
  data(){
    return{
      boardpage : this.$store.state.boardpage,
      maxpage : this.$store.state.maxpage
    }
  }
  ,

  computed:{
    ...mapState(["boardlist"])
  },
  methods:{
    ...mapActions(['boardDetail','boardWrite','boardList']),
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
    
    }
  },
  components:{
      sample
  }
}
</script>