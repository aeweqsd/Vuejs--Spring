<template>
    <v-flex xs12>
        <table>
            <tr>
                <td colspan="2" style="font-weight:700;"> {{board_detail.boardname}}</td>
            </tr>
            <tr style="font-size:0.9rem;">
                <td> 작성자 ㅣ {{board_detail.boardwriter}}</td>
                <td style="text-align:right">{{board_detail.Datetime}}</td>
            </tr>
            <tr>
                <td colspan="2" style="border:none;text-align:center;border-top:3px double #ededed">
                    {{board_detail.boardcontent}}
                </td>
            </tr>
            <tr>
                <td style="text-align:right">
                    <v-btn @click="BoardDelete({boardid:boardid})">삭제</v-btn>
                </td>
            </tr>
        </table>
    </v-flex>
</template>
<script>
import {mapActions,mapState} from "vuex"
export default {
    data(){
        return {
            boardid : this.$route.params.boardid,
        }
    },
    computed:{
        ...mapState(["board_detail"])
    },
    methods:{
        ...mapActions(["BoardDelete"])
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