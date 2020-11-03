<template>
<v-container>
    <v-flex xs12>
        <table>
            <tr>
                <td colspan="2" style="font-weight:700;"> {{board_detail.boardname}}</td>
            </tr>
            <tr style="font-size:0.9rem;">
                <td> 작성자 ㅣ {{board_detail.boardwriter}} 조회수 | {{board_detail.hit}}</td>
                <td style="text-align:right">{{board_detail.datetime}}</td>
            </tr>
            <tr>
                <td colspan="2" style="border:none;text-align:center;border-top:3px double #ededed">
                    {{board_detail.boardcontent}}
                </td>
            </tr>
            <tr>
                <td style="padding-left : 35%">
                    <div v-if="board_detail.boardwriter ===userid" style="width:150px">
                        <v-btn
                        @click="aa()"
                        fab
                        color="indigo"
                        outlined
                        dark><v-icon dark>
                             mdi-reload
                        </v-icon></v-btn>
                        <v-btn @click="BoardDelete({idboard:idboard})"
                        fab
                        dark
                        color="red"
                        outlined><v-icon dark>mdi-delete</v-icon>
                        </v-btn>
                    </div>
                </td>
            </tr>
        </table>
        <v-flex offset-xs10>
            <v-dialog
                  v-model="dialog"
                  persistent
                  max-width="600px"
             >
             <template v-slot:activator="{on, attrs}">
                <v-btn
                    v-bind="attrs"
                    v-on="on" class="mx-2" fab dark color="cyan" @click="aaa({userid:userid})"> 
                        <v-icon dark> 
                            mdi-pencil 
                        </v-icon>
                    </v-btn>
             </template>
             <v-card>
                <v-card-title>
                    <span class="headline">Note</span>
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
                            v-model="board_detail.boardwriter"
                            disabled
                            ></v-text-field>
                            </v-col>
                            <v-col
                            cols="12"
                            sm="10"
                            md="10">
                            <v-text-field
                            label="내용"
                            v-model="noteval"
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
                    @click="dialog= false">
                        Close</v-btn>
                    <v-btn
                    color="blue darken-1"
                    text
                    @click="send({idreceiver:board_detail.boardwriter,content:noteval,idsender:userid}), dialog=false">
                    Send</v-btn>
                </v-card-action>
             </v-card>
            </v-dialog>
        </v-flex>
            <v-textarea
            name="comment"
            v-model="comment"
            auto-grow
            label="댓글"
            hint="댓글을 쓸때에는 생각하시고 쓰세요"
            clearable
            ></v-textarea>
            <v-btn block @click="CommentWrite({idboard:idboard,comment:comment,userid:userid}); clear();">
                댓글 작성
            </v-btn>
              <v-pagination
            v-model="page"
            :length="(commentList.length)/5 +1"
            >
            </v-pagination>
            <template v-for="(item) in listpaging(commentList,page)">
          <v-card
        elevation="2"
        :key ="item.idcomment">
                <v-card-title v-html="item.comment"></v-card-title>
                    <v-card-subtitle v-html="time(item.timediff)"></v-card-subtitle>
                    <v-card-subtitle v-html="item.writerid"></v-card-subtitle>
                    <div v-if="item.writerid === userid"><v-card-text><v-icon @click="CommentDelete({idcomment:item.idcomment})">mdi-trash-can</v-icon></v-card-text></div>            
    </v-card>
            </template>
    </v-flex>
</v-container>
</template>

<script>
import {mapActions,mapState} from "vuex"
export default {
    
    created(){
        this.$store.dispatch("selectcomment",this.$route.params.idboard)        

    },

    data(){
        return {
            userid  : this.$store.state.Userinfo.User_Id,
            idboard : this.$route.params.idboard,
            page : 1,
            dialog: false
        }
    },
    computed:{
        ...mapState(["board_detail","commentList",'search'])
    },
    methods:{
        ...mapActions(["BoardDelete","CommentWrite","CommentDelete","sendnote"]),
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
        listpaging: function(a,b){
            return a.slice((b-1)*5,b*5)
    },
        clear: function(){
            this.comment= "";
    },
        aaa:function(a){
            console.log(a)
        },
        send:function(a){
            this.$store.dispatch("sendnote",a)
            
        },
        aa:function(){
            console.log(" ")
        }
    },
    filters:{
        highlight:function(value){
            var iq = new RegExp(value,"ig")
            return value.toString().replace(iq,function(matched,a,b){
                return ('<div class="text.text-decoration-underline">'+matched+'</div>');
            })
        }
    }
}
</script>
<style scoped>
table {
  width:600px;
  margin:0 auto;
  border-collapse:collapse;
  text-align: center;
}
table tr td {
  border-top:1px solid #ededed;
  border-bottom:1px solid #ededed;
  padding:10px;
}

.highlight{
    background-color:yellow
}
</style>