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
                  <v-col class="text-center">
                      <span>Source</span>
              
                  </v-col>
                </v-row>
              </v-container>
            </v-main>
        </v-container>
 </v-app>
</template>
<script>
import {mapAction,mapState} from "vuex"


  export default {
    created(){
      this.$store.dispatch('islogin')
     },
    props: {
      source: String,
    },
    data: () => ({
      drawer : null
    }),
    methods:{
    },
    computed:{
      menu(){
       return this.$store.state.menu;
      }      
    }
  }
</script>
