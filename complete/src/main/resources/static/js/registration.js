$(function () {
    var Characters = Backbone.Collection.extend({
        url:"/service/getCharacters",
        initialize:function () {
            this.fetch();
        }
    });
    var User = Backbone.Model.extend({

    });
    var Units = Backbone.Collection.extend({
        url:"/service/getUnits",
        initialize:function () {
            this.fetch();
        }
    });
    var AppState = Backbone.Model.extend({
        defaults:{
            state: "user",
            nickError: "",
            passwordError: "",
            jabberError: "",
            user:{
                nick:"",
                password:"",
                jabber:""
            }
        }
    });
    var Check = Backbone.Model.extend({
        url:"/service/checkNick"
    });

    var appState = new AppState();
    var units = new Units();
    var characters = new Characters();
    var check = new Check();
    var user=new User();
    appState.set({user:user});

    var Controller = Backbone.Router.extend({
        routes: {
            "": "user",
            "!/": "user",
            "!/character": "character"
        },

        character: function () {
            appState.set({ state: "character" });
        },

        user: function () {
            appState.set({ state: "user" });
        }
    });

    var controller = new Controller();

    var Registration = Backbone.View.extend({
        el: $("#block"),

        templates: {
            "user": _.template($('#user').html()),
            "character": _.template($('#character').html())
        },

        events: {
            "change input#nick": "checkNick",
            "change input#password": "checkPassword",
            "change input#jabber": "checkJabber",
            "click input#char": "goToCharacter"
        },

        initialize:function () {
            this.model.bind('change', this.render, this);
        },

        checkNick: function () {
            var nick = document.getElementById("nick");
            var user=this.model.get("user");
            user.nick=nick.value;
            appState.set({nickError:"",user:user});
            if (nick.value.length<3){
                appState.set({nickError:"Ник должен содержать минимум 3 символа"});
            }else {
                check.fetch({
                    data:{
                        nick:nick.value
                    },
                    success:function () {
                    if(check.attributes.resp==="true"){
                        appState.set({nickError:"Ник занят"});
                    }
                }})
            }
        },

        checkPassword:function () {
            var password = document.getElementById("password");
            var user=this.model.get("user");
            user.password=password.value;
            appState.set({passwordError:"",user:user});
            if(password.value.length<3){
                appState.set({passwordError:"Пароль должен содержать минимум 3 символа"});
            }
        },

        checkJabber:function () {
            var jabber = $("input#jabber").get(0);
            var user=this.model.get("user");
            user.jabber=jabber.value;
            appState.set({jabberError:"", user: user})
        },

        checkFilled:function () {
            var ret=true;
            ret=(this.model.get("jabberError").length+this.model.get("nickError").length+this.model.get("passwordError").length)===0;
            if($("input#nick").get(0).value.length===0){
                appState.set({nickError:"Поле обязательно для заполнения"});
                ret=false;
            }
            if($("input#password").get(0).value.length===0){
                appState.set({passwordError:"Поле обязательно для заполнения"});
                ret=false;
            }
            if($("input#jabber").get(0).value.length===0){
                appState.set({jabberError:"Поле обязательно для заполнения"});
                ret=false;
            }
            return ret;
        },

        goToCharacter:function () {
            if(this.checkFilled()) {
                appState.set({
                    state: "character",
                    user: {
                        nick: $(this.el).find("input#nick").value,
                        password: $(this.el).find("input#password").value,
                        jabber: $(this.el).find("input#jabber").value
                    }
                })
            }
        },

        render:function () {
            var state = this.model.get("state");
            $(this.el).html(this.templates[state](this.model.toJSON()));
            return this;

        }

    });
    var block = new Registration({ model: appState });

    appState.trigger("change");

    appState.bind("change:state", function () {
        var state = this.get("state");
        if (state == "user")
            controller.navigate("!/", false);
        else
            controller.navigate("!/" + state, false);
    });

    Backbone.history.start();
});