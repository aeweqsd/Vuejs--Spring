<template>
    <v-container>
        <v-layout>
            <v-row>
                <v-col
                cols="9"
                >
                    <v-alert
                        color="indigo"
                        dark
                        dense>현재 읽지 않은 메세지가 {{note.length}}개 있습니다.</v-alert>
            </v-col>
            <v-col
            cols="9">
                <template v-for="(item) in note">
                <v-card
                :key="item.idnote" outlined
                style="z-index:2"
                tile>
                <v-list-item three-line>
                    <v-list-item-title>
                    <v-icon>{{ icons.mdiAccount }}</v-icon> | {{item.idsender}}
                    </v-list-item-title>
                </v-list-item>
                <v-list-item-subtitle
                class="ml-5"><v-icon>{{ icons.mdiMessage }}</v-icon> | {{item.content}}</v-list-item-subtitle>
                <v-card-actions>
                    <v-btn
                         text
                         color="primary"
                         style="margin-left : 400px"
                         v-if="!item.is_read"
                         @click="reading({idnote:item.idnote});"
                    >읽음표시</v-btn>
                    <v-btn
                        outlined
                        rounded
                        text
                    > 답장보내기</v-btn>
                </v-card-actions>
                </v-card>
                </template>
            </v-col>
            </v-row>
        </v-layout>
    </v-container>

    
</template>
<script>
import {mapActions,mapState} from "vuex"
import {
    mdiAccount,
    mdiPencil,
    mdiShareVariant,
    mdiDelete,
    mdiMessage
  } from '@mdi/js'
export default {
    created(){
        this.$store.dispatch("selectNote",this.$store.state.Userinfo.User_Id)
        console.log("mdiAccount")
    },    
    data: () => ({
      icons: {
        mdiAccount,
        mdiPencil,
        mdiShareVariant,
        mdiDelete,
        mdiMessage
      },
    }),
    computed:{
        ...mapState(["note"])
    },
    methods:{
        ...mapActions(["noteread"]),
        reading: function(a){
            console.log(a)
            this.$store.dispatch("noteread",a.idnote)
        }
    }
    
}
</script>