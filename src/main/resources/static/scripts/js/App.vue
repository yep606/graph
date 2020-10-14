<template>
    <div id="app">
        {{info}}
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        data() {
            return {
                info: null
            }
        },
        mounted() {
            axios
                .get('http://localhost:8080/task')
                .then(response => (this.info = response.data))
        }
    }
</script>

<style>

</style>