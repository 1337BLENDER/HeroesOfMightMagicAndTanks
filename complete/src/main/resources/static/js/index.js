$(function () {

    var Leaders = Backbone.Collection.extend({
            url: "/service/getLeaderboard"
        }
    );

    var leaders = new Leaders();

    var AppState = Backbone.Model.extend({
        defaults: {
            state: "description",
            players: [{name:"Adam", winrate:"100"},{name:"sdsd", winrate:"234"}],
        }
    });

    var appState = new AppState();

    var Controller = Backbone.Router.extend({
        routes: {
            "": "description",
            "!/": "description",
            "!/leaders": "leaderboard"
        },

        description: function () {
            appState.set({ state: "description" });
        },

        leaderboard: function () {
            appState.set({ state: "leaders" });
        }
    });

    var controller = new Controller();


    var Block = Backbone.View.extend({
        el: $("#block"),

        templates: {
            "description": _.template($('#description').html()),
            "leaders": _.template($('#leaderboard').html())
        },

        events: {
            "click input#descr": "setDescr",
            "click input#lead": "setLead",
            "click input#reg": "goToRegistration"
        },

        resetErrorLabel: function () {
            if(window.location.search==='?error'){
                document.getElementById("loginError").innerHTML="неверный логин или пароль";
            }else {
                document.getElementById("loginError").innerHTML="";
            }
        },

        initialize: function () {
            this.model.bind('change', this.render, this);
            this.resetErrorLabel();
        },

        setDescr: function () {
            appState.set({
                "state": "description"
            });
            this.resetErrorLabel();
        },

        setLead: function () {
            var params={
                data:{
                    limit: "5"
                },
                success:function () {
                    appState.set({
                        "players": leaders.toJSON(),
                        "state": "leaders"
                    });
                }
            };
            this.resetErrorLabel();
            leaders.fetch(params);
        },

        goToRegistration: function () {
            window.location.hash="";
            window.location.pathname="/registration";
        },

        render: function () {
            var state = this.model.get("state");
            $(this.el).find("#content").html(this.templates[state](this.model.toJSON()));
            return this;
        }
    });

    var block = new Block({ model: appState });

    appState.trigger("change");

    appState.bind("change:state", function () {
        var state = this.get("state");
        if (state == "description")
            controller.navigate("!/", false);
        else
            controller.navigate("!/" + state, false);
    });

    Backbone.history.start();


});