$(function () {

    var Leader = Backbone.Model.extend(
        {
            defaults:{
                name: "leader",
                winrate: "100"
            }
        }
    );

    var Leaders = Backbone.Collection.extend({
            model: Leader,
            url: "/service/getLeaderboard",
            defaults: [{name:"name",winrate:"2"},{name:"name",winrate:"2"}]
        }
    );

    var leaders = new Leaders();

    var AppState = Backbone.Model.extend({
        defaults: {
            state: "description",
            players: [{name:"Adam", winrate:"100"},{name:"sdsd", winrate:"234"}],
            route: window.location
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

        initialize: function () {
            this.model.bind('change', this.render, this);
        },

        setDescr: function () {
            appState.set({
                "state": "description"
            });
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
            leaders.fetch(params);
        },

        goToRegistration: function () {
            window.location.hash="";
            window.location.pathname+="registration";
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