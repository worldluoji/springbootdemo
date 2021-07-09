<template>
    <div class="bk">
        <div>
            <img  class="logo" src="../assets/logo.jpg">
        </div>
        <div>
            <div class="search-area">
                <input type="search" id="search-input" name="search-input" 
                placeholder="search user by name or address" v-model="keyword" @keyup.enter="getUserInfo"/>
            </div>
        </div>
        <div class="results" v-show="results && results.length > 0">
            <table class="pure-table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>AccountNumber</th>
                        <th>Name</th>
                        <th>City</th>
                        <th>Address</th>
                        <th>Balance</th>
                    </tr>
                </thead>
            
                <tbody>
                    <tr v-for="(item,index) in results" :key="item.accountNumber">
                        <td>{{ index + 1}}</td>
                        <td>{{ item.accountNumber }}</td>
                        <td>{{ item.firstname}} {{ item.lastname }}</td>
                        <td>{{ item.city }}</td>
                        <td>{{ item.address }}</td>
                        <td>{{ item.balance }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    created () {
        this.lastTime = 0;
    },
    data () {
        return {
            keyword: '',
            results: [],
            options: [],
            lastTime: 0
        } 
    },
    methods: {
        async getUserInfo () {
            if (this.keyword == null || this.keyword === '') {
                return
            }
            axios.post("/search", {
                keyword: this.keyword
            })
            .then( (response) => {
                if (response.status === 200) {
                    console.log(response.data);
                    this.results = response.data;
                }
            })
            .catch( (error) => {
                console.log(error);
            });
        },
        async getSuggustOptions (newVal) {
            if (newVal == null || newVal === '') {
                return
            }
            axios.post("/suggest", {
                keyword: newVal
            })
            .then( (response) => {
                if (response.status === 200) {
                    console.log(response.data);
                    this.options = response.data;
                }
                this.lastTime = 0;
            })
            .catch( (error) => {
                console.log(error);
                this.lastTime = 0;
            });
        }
    },
    watch: {
        keyword (newVal) {
            if (this.lastTime === 0) { 
                this.lastTime = setTimeout(()=>{
                    this.getSuggustOptions(newVal);
                }, 2000);
            } else{
                clearTimeout(this.lastTime)
                this.lastTime = setTimeout(()=>{
                   this.getSuggustOptions(newVal);
                }, 2000);
            }
        }
    }
}
</script>

<style scoped>
    .logo {
        width: 25vw;
        border-radius: 50%;
        margin-top: 2vh;
    }

    .bk {
        display: grid;
        /* place-items: center;*/
        background: lightblue;
        resize: both;
        overflow: auto;
        width: 100vw;
        height: 100vh;
        grid-template-rows: auto auto 1fr;
    }

    .search-area {
        margin-top: 3vh;
		padding: 0.5rem;
        text-align: center;
    }

    .results {
        margin-top: 3vh;
    }

    #search-input {
        padding: 0.5rem;
        font-size: 1rem;
        border-radius: 30px;
        width: 36vw;
        opacity: 1;
        outline: none;
        text-align: center;
    }

    table {
        border-collapse: collapse;
        border-spacing: 0;
        margin: auto;
    }
    
    td,th {
        padding: 0;
    }
    
    .pure-table {
        border-collapse: collapse;
        border-spacing: 0;
        empty-cells: show;
        border: 1px solid #cbcbcb;
    }
    
    .pure-table caption {
        color: #000;
        font: italic 85%/1 arial,sans-serif;
        padding: 1em 0;
        text-align: center;
    }
    
    .pure-table td,.pure-table th {
        border-left: 1px solid #cbcbcb;
        border-width: 0 0 0 1px;
        font-size: inherit;
        margin: 0;
        overflow: visible;
        padding: .5em 1em;
    }
    
    .pure-table thead {
        background-color: #e0e0e0;
        color: #000;
        text-align: left;
        vertical-align: bottom;
    }
    
    .pure-table td {
        background-color: transparent;
    }
</style>