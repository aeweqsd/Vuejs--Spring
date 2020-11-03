<template>
    <v-container fluid>
        <v-layout>
            <v-card
            width="100%"
            max-width="100%">
            <v-tabs
            >
                <v-tab
                @change="alert(0)"
                >내가 받은 메세지</v-tab>
                <v-tab
                @change="alert(1)">내가 보낸 메세지</v-tab>
            </v-tabs>
            <v-row>
                <v-col
                cols="12">
                <v-dialog
                v-model="Reply"
                persistent
                max-width="600px">
                <template v-slot:activator="{on,attrs}">
                    <v-flex
                    v-bind="attrs"
                    v-on ="on"
                    ></v-flex>
                </template>
                <v-card>
                    <v-card-title>
                        <span class="headline">Replynote</span>
                    </v-card-title>
                    <v-card-text>
                        <v-container>
                            <v-row>
                                <v-col
                                cols="12"
                                sm="6"
                                md="4"
                                ><v-text-field
                                label="받는이"
                                v-model="Recevier"
                                disabled></v-text-field>
                                </v-col>
                                <v-col
                                cols="12"
                                sm="10"
                                md="10">
                                <v-text-field
                                label="내용"
                                v-model="content"
                                required></v-text-field>
                                </v-col>
                            </v-row>
                        </v-container>
                    </v-card-text>
                    <v-card-action>
                        <v-spacer></v-spacer>
                        <v-btn
                        color="blue darken-1"
                        text
                        @click="Reply=false">Close</v-btn>
                        <v-btn
                        color="blue darken-1"
                        text
                        @click="send({idsender:userid,content:content,idreceiver:Recevier}), Reply=false">Send</v-btn>
                    </v-card-action>
                </v-card>
                </v-dialog>
                </v-col>
                <v-col
                cols="9"
                >
                    <v-alert
                        v-if= "this.rev==0"
                        color="indigo"
                        dark
                        dense>현재 읽지 않은 메세지가 {{note.filter(i=>i.is_read == false).length}}개 있습니다.</v-alert>
            </v-col>
            <v-col
            cols="9">
                <template v-for="(item) in notepaging(note,page)">
                <v-card
                :key="item.idnote" outlined
                style="z-index:2"
                tile>
                <v-list-item three-line>
                    <v-list-item-title>
                    <div v-if="rev == 0"><v-icon>{{ icons.mdiAccount }}</v-icon>| {{item.idsender}}</div> 
                    <div v-if="rev==1"><v-icon>{{ icons.mdiAccount }}</v-icon>| {{item.idreceiver}}</div>
                    </v-list-item-title>
                </v-list-item>
                <v-list-item-subtitle
                class="ml-5"><v-icon>{{ icons.mdiMessage }}</v-icon> | {{item.content}}</v-list-item-subtitle>
                <v-card-actions>
                    <v-flex
                    style="margin-left:60%"
                    
                    >
                    <v-btn
                         text
                         color="primary"
                         v-if="!item.is_read && rev==0"
                         @click="reading({idnote:item.idnote});"
                    >읽음표시</v-btn>
                     <v-btn
                         text
                         color="primary"
                         v-if="!item.is_read && rev==1"
                         disabled
                    >읽음표시</v-btn>
                    <v-btn
                        color="blue-grey"
                        text
                        @click="openda({sender:item.idsender})"
                    > 답장보내기</v-btn>
                    <v-btn
                        color="default"
                        text
                        @click="aaa({idnote:item.idnote});" >삭제</v-btn>
                    </v-flex>
                </v-card-actions>
                </v-card>
                </template>
            </v-col>
            </v-row>
            <v-pagination
            v-model="page"
            :length="paging(note.length)"
            @input="abc(page)"></v-pagination>
            </v-card>
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
        this.$store.dispatch("selectNote",[this.$store.state.Userinfo.User_Id,0])
    },    
    data(){
        return{
    
      icons: {
        mdiAccount,
        mdiPencil,
        mdiShareVariant,
        mdiDelete,
        mdiMessage
      },
      Reply: false,
      Recevier:'',
      userid : this.$store.state.Userinfo.User_Id,
      rev : 0,
      page: 1,
    }
    },
    computed:{
        ...mapState(["note"])
    },
    methods:{
        ...mapActions(["noteread","notedelete","sendnote"]),
        reading: function(a){
            console.log(a)
            this.$store.dispatch("noteread",a.idnote)
        },
        aaa:function(a){
            this.$store.dispatch("notedelete",a.idnote)
        },
        openda:function(a){
            console.log(" ")
            this.Recevier=a.sender
            this.Reply=true
        },
        send:function(a){
            this.$store.dispatch("sendnote",a)
        },
        alert:function(a){
            this.rev=a
            if(a==0){
                this.$store.dispatch("selectNote",[this.userid,0])
            }
            else{
                this.$store.dispatch("selectNote",[this.userid,1])
            }
        },
        condition:function(a){
            if(this.rev ==1){
                return a
            }
            return a&&(this.rev==0)
            
        },
        abc:function(a){
            console.log(a)
        },
        notepaging: function(a,b){
            return a.slice((b-1)*5,b*5)
    },
        paging:function(a){
            if(a%5 ==0){
                return a/5
            }else{
                return a/5+1
            }
        }
    }
    
}
</script>