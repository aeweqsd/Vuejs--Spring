<template>
    <v-container>  
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
                <tr
                v-for="(item,index) in Keyword.sort(function(x,y){
                  return y[1]-x[1];  
                })"
                :key="index"
                >
                <td>{{item[0]}}</td>
                <td>{{item[1]}}</td>
                <td><v-checkbox
                    input-value="true"
                    value
                ></v-checkbox></td>
                </tr>
            </tbody>
        </template>
    </v-simple-table>
    <v-flex offset-xs7>
        <v-btn color="primary" @click="InitKeyword()">activated</v-btn>
        <v-btn color="secondary" @click="selectKeyword()">Select</v-btn>
        <v-btn>Insert DB</v-btn>
    </v-flex>
    </v-container>
</template>
<script>
import {mapActions,mapState} from "vuex"
export default {
    computed:{
        ...mapState(["Keyword"])
    },
    methods:{
        ...mapActions(["InitKeyword"]),
        selectKeyword(){
            
            console.log()
        }
    }
}
</script>