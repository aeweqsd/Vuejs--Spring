<template>
    <v-container>  
        <v-flex offset-xs6>
            <v-slider
            label="Frequency"
            max="17"
            :thumb-size="24"
            thumb-label="always"
            v-model="media"
            @change="aa()"></v-slider>
        </v-flex>
    <v-flex xs12>
    <v-simple-table>
        <template v-slot:default>
            <thead>
                <tr>
                    <th class="text-left">
                        Keyword
                    </th>
                    <th class="text-left">
                        frequency
                    </th>
                    <th>
                        Insert
                    </th>
                </tr>
            </thead>
            <tbody>
                <!-- <tr
                v-for ="[key,value] of sortMap(test)"
                :key="key">
                <td>{{key}}</td>
                <td>{{value}}</td>
                </tr> -->
                <tr
                v-for="(item,index) in Keyword.sort(function(x,y){
                  return y[1]-x[1];  
                })"
                :key="index"
                >
                <td>{{item[0]}}</td>
                <td>{{item[1]}}</td>
                <td>
                   <v-btn
                   depressed
                   color="error"
                   @click="selectKeyword(item)"
                   >삭제</v-btn>
                </td>
                </tr>
            </tbody>
        </template>
    </v-simple-table>
    </v-flex>
    <v-flex offset-xs7>
        <v-btn color="primary" @click="InitKeyword()">activated</v-btn>
        <v-btn @click="InputKeyword()">Insert DB</v-btn>
    </v-flex>
    </v-container>
</template>
<script>
import {mapActions,mapState} from "vuex"
export default {
    data(){
        return {
            media:0
        }
    },
    computed:{
        ...mapState(["Keyword","test"])
    },
    methods:{
        ...mapActions(["InitKeyword","ChangeKeyword","InputKeyword"]),
        selectKeyword(a){
            console.log(a)
            let arr = this.$store.state.Keyword
            let i =0;
            while(true){
                if(arr[i][0] === a[0]){
                    break;
                }
                i++
            }
            arr.splice(i,1)
            this.$store.dispatch("ChangeKeyword",arr)
        },
        aa(){
            console.log(this.media)
            let arr = this.$store.state.Keyword
            console.log(arr.length)
            console.log(arr[0][1])
            let i =0;
            while(true){
                if(i == arr.length){
                    if(arr[i][1] >=this.media){
                        break;
                    }else{
                   arr =['']
                   break;
                    }
                }
                if(arr[i][1] <=this.media){
                    arr = arr.slice(0,i)
                    break;
                }
                i++;
            }
           /* for(let i=0;i<arr.length;i++){
                if(arr[i][1] >this.media){
                    arr = arr.slice(i);
                }
            }*/
            this.$store.dispatch("ChangeKeyword",arr)
        },
        sortMap(a){
            console.log(a)
            let q = new Map([...a.entries()].sort((a, b) => b[1] - a[1]));
            return q
        }
    
    }
}
</script>