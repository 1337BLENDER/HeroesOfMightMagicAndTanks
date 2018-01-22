$(function () {

    var Leaders = Backbone.Model.extend(
        {
            defaults:{
                leaders: [{name:"Leader", winrate:"100"}]
            },
            urlRoot:"/service/getLeaderboard",

            initialize: function () {
                var params={
                  data:{
                      "limit":"5"
                  }
                };
                var data = this.fetch(params);
            }
        }
    );

    var leaders = new Leaders();

    var AppState = Backbone.Model.extend({
        defaults: {
            state: "description",
            players: leaders.leaders,
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
            appState.set({
                "state": "leaders"
            });
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