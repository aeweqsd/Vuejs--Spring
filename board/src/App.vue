<template>
 <v-app id="inspire">
        <v-navigation-drawer
        v-model="drawer"
        app
        >
            <v-list dense>
                <v-subheader>메뉴</v-subheader>
                    <v-list-item
                    v-for="(item,i) in menu"
                    :key="i"
                    :to="item.link">
                    <v-list-item-icon>
                        <v-icon v-text="item.icon"></v-icon>
                            <v-list-content>
                                <v-list-item-title v-text="item.text"></v-list-item-title>
                            </v-list-content>
                    </v-list-item-icon>
                    </v-list-item>
            </v-list>
        </v-navigation-drawer>
          <v-app-bar
      app
      color="primary"
      dark
      elevation="0"
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>Example</v-toolbar-title>
      <v-row>
      <v-col offset-md="7"
      style="padding-top:24px"
      >
         <v-text-field
         append-icon="mdi-magnify"
         max-width="400"
         outlined
         dark
         autofocus
         v-model="query"
         @keyup ="aaa()"
         @keydown.enter ="bbb()"
         @blur="ccc()"
         >
         </v-text-field>
      </v-col>
      <v-col offset-md="7"
      style="position: relative ; top:159px; margin-top : -200px"
      >
          <v-card
          class="mt-auto"
          max-width="400"
          style="z-index:1"
          light
          flat
          outlined
          v-show="searchlist"
          v-for="item in searchlist"
          :key="item"
          @click="completekeyword(item)"
          >
         <v-list>
           <v-list-item-content>
           <v-list-item-title v-html="item"></v-list-item-title>
           </v-list-item-content>
         </v-list>
         </v-card>
      </v-col>
           </v-row>
    </v-app-bar>
        <v-container>
            <v-main>
              <v-container
                class="fill-height"
                fluid
              >
                <router-view/>
                <v-row
                  align="center"
                  justify="center"
                >
                </v-row>
              </v-container>
            </v-main>
        </v-container>
 </v-app>
</template>
<script>
import {mapActions,mapState} from "vuex"


  export default {
    created(){
      this.$store.dispatch('islogin')
     },
    props: {
      source: String,
    },
    data: () => ({
      drawer : null,
      query : ''
    }),
    methods:{
      ...mapActions(['Search_List','Search']),
      search_list(){
        console.log("clicked")
        this.$store.dispatch("Search_List")
      },
      updateInput: function(event) {
      var updatedText = event.target.value;
      console.log(updatedText)
      this.inputText = updatedText;
    },
    aaa(){                //추천어 검색
      if(this.query.length <3){
        this.$store.state.searchlist = []
        return
      }else{
        this.$store.dispatch("Search_List",this.query)
      }
    },
    bbb(){
      if(this.query.length <3){
        return
      }     
      this.$store.dispatch("Search",this.query)          
    },
    ccc(){
      let ab = this.$store
      setTimeout(function(){
      ab.state.searchlist =[]
    },400)
    },
    completekeyword(item){
       this.$store.dispatch("Search",item)
    }
    },
    computed:{
      ...mapState(['searchlist']),
      menu(){
       return this.$store.state.menu;
      }      
    }
  }
</script>

