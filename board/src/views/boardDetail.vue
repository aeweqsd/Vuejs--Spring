<template>
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
                <td style="text-align:right">
                    <div v-if="board_detail.boardwriter ===userid"><v-btn @click="BoardDelete({idboard:idboard})">삭제</v-btn></div>
                </td>
            </tr>
        </table>
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
            page : 1
        }
    },
    computed:{
        ...mapState(["board_detail","commentList"])
    },
    methods:{
        ...mapActions(["BoardDelete","CommentWrite","CommentDelete"]),
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
</style>