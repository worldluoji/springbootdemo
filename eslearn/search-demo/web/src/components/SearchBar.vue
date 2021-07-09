<template>
    <div class="bk">
        <div>
            <img  class="logo" src="../assets/logo.jpg">
        </div>
        <div>
            <div class="search-area">
                <input type="search" id="search-input" name="search-input" autocomplete="off"
                placeholder="search user by name or address" v-model="keyword" @keyup.enter="getUserInfo"/>
                <div class="options" v-show="options && options.length > 0">
                    <ul class="option-list">
                        <li calss="option-item" v-for="(option,index) in options" :key="index" @click="choose(option)">
                            <span>{{ option }}</span>
                        </li>
                    </ul>
                </div>
            </div>
            <div>
                <input type="button" id="search-btn" value="search" @click="getUserInfo"/>
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
            this.options = []
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
        },
        choose (text) {
            this.options = [];
            this.keyword = text;
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
        margin-top: 5vh;
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
        text-align: center;
    }

    #search-btn {
        margin-top: 1vw;
        font-size: 1rem;
        padding: 0.3rem;
        border-radius: 5px;
    }

    .results {
        margin-top: 3vh;
    }

    #search-input {
        padding: 0.5rem;
        font-size: 1rem;
        border-radius: 3px;
        width: 36vw;
        opacity: 1;
        outline: none;
        text-align: left;
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

    .options {
        display: absolute;
        margin-top: 0;
    }

    .option-list {
        list-style-type: none;
        padding: 0;
        margin: 0;
    }

    li span:hover {
        background-color: rgba(245, 245, 245, 0.933);
    }

    li span {
        border: 1px solid #ddd; /* 链接添加边框 */
        background-color: white; 
        text-decoration: none;
        font-size: 1rem;
        padding: 0.5rem; 
        color: black; 
        display: block;
        width: 34vw; 
        margin: auto;
    }

</style>