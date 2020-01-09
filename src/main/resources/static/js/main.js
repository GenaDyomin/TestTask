function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}


var appearApi = Vue.resource('/page{/id}')


Vue.component('appear-form', {
    props: ['appears', 'appearAttr'],
    data: function () {
        return {
            id: '',
            date: new Date().toJSON().slice(0, 10).replace(/-/g, '-'),
            selected: '',
            text: '',
            name: '',
            lattitude: '',
            longtitude: ''
        }
    },
    watch: {
        appearAttr: function (newVal) {
            this.date = new Date().toJSON().slice(0, 10).replace(/-/g, '-');
            this.selected = newVal.type;
            this.text = newVal.text;
            this.name = newVal.name;
            this.id = newVal.id;
            this.lattitude = newVal.lattitude;
            this.longtitude = newVal.longtitude;

        }
    },
    template: '<div>' +
        '<span> <em>Date: </em>{{ date }} <em>Type:</em>  </span>    ' +
        '<select v-model="selected">' +
        '  <option>accident</option>' +
        '  <option>review</option>' +
        '  <option>inquiry</option>' +
        '</select>' +
        '<input type ="text" placeholder="Write description" v-model="text" /> ' +
        '<input type ="text" placeholder="lattitude" v-model="lattitude" />'+
        '<input type ="text" placeholder="longtitude" v-model="longtitude" />'+
        '<input type="button" value="Add" @click ="save">' +
        '</div>',
    methods: {
        save: function () {
            var appear = {
                id: this.id,
                date: this.date,
                type: this.selected,
                text: this.text,
                userName: frontendData.profile.name,
                lattitude: this.lattitude,
                longtitude: this.longtitude
            };
            if (this.id) {
                appearApi.update(appear).then(result =>
                result.json().then(data => {
                    var index = getIndex(this.appears, data.id);
                this.appears.splice(index, 1, data);
                this.text = ''
                this.date = new Date().toJSON().slice(0, 10).replace(/-/g, '-')
                this.name = ''
                this.id = ''
                this.lattitude = ''
                this.longtitude = ''
            })
            )
            } else {
                appearApi.save({}, appear).then(result => result.json().then(data => {
                    this.appears.push(data);
                this.text = ''
                this.userName = ''
                this.date = new Date().toJSON().slice(0, 10).replace(/-/g, '-')
                this.id = ''
                this.lattitude = ''
                this.longtitude = ''
            })
            )
            }
        }
    }
});

Vue.component('appear-row', {
    props: ['appear', 'editAppear', 'appears'],
    template: '<div style = "width: 200%">' +
        '<span>{{ appear.id }} {{ appear.date }} {{ appear.type }} {{ appear.text }}  {{ appear.userName }} {{appear.lattitude}} {{appear.longtitude}}</span>'+
        '<button @click="edit">Edit</button>'+
        '<button @click="del"> X </button>'+
        '<br>'+
        '</div>',
    methods: {
        edit: function () {
            this.editAppear(this.appear);
        },
        del: function () {
            appearApi.remove({id: this.appear.id}).then(result => {
                if(result.ok
        )
            {
                this.appears.splice(this.appears.indexOf(this.appear), 1)
            }
        })
        }
    }
});

Vue.component('appears-list', {
    props: ['appears'],
    data: function () {
        return {
            appear: null
        }
    },
    template: '<div style="position: relative; width: 300px;">' +
        '<appear-form :appears ="appears" :appearAttr ="appear"/>' +
        '<appear-row v-for ="appear in appears" :key="appear.id" :appear = "appear" :editAppear = "editAppear" :appears="appears"/></div>',
    methods: {
        editAppear: function(appear) {
            this.appear = appear;
        }
    }
});

var app = new Vue({
    el: '#app',
    template:'<div>' +
        '<div v-if="!profile">Login required! <a href="/login">Google</a></div>' +
        '<div v-else>' +
        '<div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>' +
        '<input type="button" value="Show map" @click ="show">' +
        '<appears-list :appears="appears" />' +
        '</div>' +
        '</div>',
    data: {
        appears: frontendData.appears,
        profile: frontendData.profile
    },
    methods: {
        show: function () {
            document.getElementById("map").style.display = "block";
        }
    },
    created: function () {/*
        appearApi.get().then(result => result.json().then(data => data.forEach(
            appear => this.appears.push(appear)
    )))*/
    },
});