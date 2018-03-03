$(function () {
    var Characters = Backbone.Collection.extend({
        url:"/service/getCharacters"
    });
    var User = Backbone.Model.extend({

    });
    var Units = Backbone.Collection.extend({
        url:"/service/getUnits"
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
            "character": _.template($('#character').html()),
            "army": _.template($('#army').html())
        },

        events: {
            "change input#nick": "checkNick",
            "change input#password": "checkPassword",
            "change input#jabber": "checkJabber",
            "click input#char": "goToCharacter",
            "click input#next": "nextChar",
            "click input#previous": "prevChar",
            "click input#toArmy": "goToArmy",
            "click input#nextUnit": "nextUnit",
            "click input#previousUnit": "prevUnit"
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
            var oterThis = this;
            var params = {
                success: function () {
                    if(oterThis.checkFilled()) {
                        appState.set({
                            state: "character",
                            charInd: 0,
                            tempChar: characters.toJSON()[0],
                            charNum: characters.length,
                            user: {
                                nick: $(this.el).find("input#nick").value,
                                password: $(this.el).find("input#password").value,
                                jabber: $(this.el).find("input#jabber").value
                            }
                        })
                    }
                }
            };
            characters.fetch(params);
        },

        goToArmy: function () {
            var pastUser=this.model.get("user");
            var outerThis = this;
            var params = {
                success: function () {
                    appState.set({
                        state: "army",
                        user: {
                            nick: pastUser.nick,
                            password: pastUser.password,
                            jabber: pastUser.jabber,
                            character: outerThis.model.get("tempChar")
                        },
                        unitInd: 0,
                        unitNum: units.length,
                        shownUnits:[units.at(0).toJSON(),units.at(1).toJSON(),units.at(2).toJSON()]
                    })
                }
            };
            units.fetch(params);
        },

        render:function () {
            var state = this.model.get("state");
            $(this.el).html(this.templates[state](this.model.toJSON()));
            return this;

        },

        nextChar:function () {
            var ind = this.model.get("charInd");
            if(ind+1<this.model.get("charNum")){
                ind++;
                appState.set({
                    charInd: ind,
                    tempChar: characters.at(ind).toJSON()
                })
            }
        },

        prevChar:function () {
            var ind = this.model.get("charInd");
            if(ind>0){
                ind--;
                appState.set({
                    charInd: ind,
                    tempChar: characters.at(ind).toJSON()
                })
            }
        },

        nextUnit:function () {
            var ind = this.model.get("unitInd");
            var shown = this.model.get("shownUnits");
            var length = shown.length;
            for(var i=0;i<length-1;i++){
                shown[i]=shown[i+1]
            }
            if(ind+length<this.model.get("unitNum")){
                shown[length-1]=units.at(ind+length).toJSON();
                ind++;
                appState.set({
                    unitInd: ind,
                    shownUnits: shown
                })
            }
        },

        prevUnit:function () {
            var ind = this.model.get("unitInd");
            var shown = this.model.get("shownUnits");
            var length = shown.length;
            for(var i=length-2;i>=0;i--){
                shown[i+1]=shown[i]
            }
            if (ind > 0) {
                ind--;
                shown[0]=units.at(ind).toJSON();
                appState.set({
                    unitInd: ind,
                    shownUnits: shown
                })
            }
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