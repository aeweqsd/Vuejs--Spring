<template>
  <v-container>
    <v-flex xs12>     
        <template v-for="(item) in boardlist">
        <v-card class="pa-3" outlined>
          <v-list three-line>
             <v-list-item
                :key ="item.idboard"
                @click="boardDetail(item.idboard)"
              >
              <v-list-item-content>
                <v-list-item-subtitle v-html="item.boardname"></v-list-item-subtitle>
                <v-list-item-subtitle v-html="time(item.timediff)"></v-list-item-subtitle>
              </v-list-item-content>
              </v-list-item>
            
          </v-list>
        </v-card>
        </template>
    </v-flex>
  </v-container>
</template>
<script>

import {mapState, mapActions} from "vuex"
import sample from '../components/BoardSubject.vue'


export default {
  created(){
    
    this.$store.dispatch('boardList')
  },
  computed:{
    ...mapState(["boardlist"])
  },
  methods:{
    ...mapActions(['boardDetail','boardWrite']),
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