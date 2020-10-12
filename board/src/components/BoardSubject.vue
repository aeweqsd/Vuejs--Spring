<template>
    <v-container>
        <v-flex xs12>
            <v-card class="pa-3">
                <v-list three-line>
                    <template v-for="(items) in boardSubject">
                        <v-list-item
                        :key ="items.subjectname"
                        @click="boardselect(items.subjectname)"
                        >
                    <v-list-item-content>
                        <v-list-item-subtitle v-html="items.subjectname"></v-list-item-subtitle>
                    </v-list-item-content>
                        </v-list-item>
                    </template>
                </v-list>
            </v-card>
        </v-flex>
    </v-container>
</template>
<script>
import {mapState,mapAction} from "vuex"
export default {

    created(){
        this.$store.dispatch('boardSubject')
    },
    computed:{
        ...mapState(["boardSubject"])
    }
}
</script>