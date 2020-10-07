<template>
  <v-container>
    <v-flex xs12>
      <v-row>
        <v-col
        sm="6">
        <v-card class="pa-3">
          <v-list three-line>
            <template v-for="(item) in boardlist">
              <v-list-item
                :key ="item.idboard"
                @click="boardDetail(item.idboard)"
              >
              <v-list-item-content>
                <v-list-item-subtitle v-html="item.boardname"></v-list-item-subtitle>
                <v-list-item-subtitle v-html="item.boardcontent"></v-list-item-subtitle>
              </v-list-item-content>
              </v-list-item>
            </template>
          </v-list>
        </v-card>
        </v-col>
     <v-col
      sm="6">
      <sample></sample>
     </v-col>
      </v-row>
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
    ...mapActions(['boardDetail','boardWrite'])
  },
  components:{
      sample
  }
}
</script>