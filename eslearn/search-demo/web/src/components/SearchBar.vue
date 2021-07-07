<template>
    <div>
        <div class="bk">
            <div>
                <img  class="logo" src="../assets/logo.png">
            </div>
            <div class="search-area">
                <input type="search" id="search-input" name="search-input" 
                placeholder="search user by name or address" v-model="keyword" @keyup.enter="getUserInfo"/>
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
    </div>
</template>

<script>
import axios from 'axios'
export default {
    data () {
        return {
            keyword: '',
            results: []
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
        }
    }
}
</script>

<style scoped>
    .logo {
        height: 30vh;
        width: 30vw;
    }

    .bk {
        display: grid;
        place-items: center;
        background: lightblue;
        resize: both;
        overflow: auto;
        width: 100vw;
        height: 100vh;
    }

    .search-area {
		padding: 0.5rem;
        text-align: center;
    }

    #search-input {
        padding: 0.5rem;
        font-size: 2rem;
        border-radius: 30px;
        width: 36vw;
        opacity: 1;
        outline: none;
    }

    table {
        border-collapse: collapse;
        border-spacing: 0;
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