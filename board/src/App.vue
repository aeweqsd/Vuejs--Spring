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
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>Example</v-toolbar-title>
      <v-flex offset-xs7>
        <v-autocomplete
        v-model="search"
      :items="this.$store.state.searchlist"
      class="mx-4"
      flat
      hide-no-data
      hide-details
      label="검색"
      solo-inverted
      @click ="search_list()"
      @keydown ="aaa()"
      append-icon="mdi-magnify"
      return object
    ></v-autocomplete>
      </v-flex>
    </v-app-bar>
        <v-container>
            <v-main>
              <v-container
                class="fill-height"
                fluid
              ><h3>{{search}}</h3>
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
      search : ''
    }),
    methods:{
      ...mapActions(['Search_List']),
      search_list(){
        console.log("clicked")
        this.$store.dispatch("Search_List")
      },
      updateInput: function(event) {
      var updatedText = event.target.value;
      console.log(updatedText)
      this.inputText = updatedText;
    },
    aaa(){
      console.log(this.search)
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
