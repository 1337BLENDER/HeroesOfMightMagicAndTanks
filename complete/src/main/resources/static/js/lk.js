$(function () {

    var User = Backbone.Model.extend({
            url: "/secure/getFullUser"
        }
    );

    var user = new User();

    var ChangeJabber = Backbone.Model.extend({
        url:"/secure/changeJabber"
    });

    var changeJabber = new ChangeJabber();

    var AppState = Backbone.Model.extend({
        defaults: {
            state: "main",
            successMessage: "",
            errorMessage: ""
        }
    });

    var appState = new AppState();

    var Controller = Backbone.Router.extend({
        routes: {
            "": "main",
            "!/": "main",
            "!/second": "second"
        },

        main: function () {
            appState.set({state: "main"});
        },

        second: function () {
            appState.set({state: "second"});
        }

    });

    var controller = new Controller();


    var Block = Backbone.View.extend({
        el: $("#block"),

        templates: {
            "main": _.template($('#main').html()),
            "second": _.template($('#second').html())
        },

        events: {
            "click input#changeJabber": "changeJabber",
            "click input#friends": "goToFriends"
        },

        initialize: function () {
            this.model.bind('change', this.render, this);
            this.getFullUser()
            },

        render: function () {
            if(this.model.get("user")!==undefined) {
                var state = this.model.get("state");
                $(this.el).html(this.templates[state](this.model.toJSON()));
                return this;
            }
        },

        getFullUser: function () {
            user.fetch({
                success: function () {
                    appState.set({
                        user: user.toJSON()
                    })
                }
            })
        },

        changeJabber: function () {
            var jabber = $("input#jabberField").get(0).value.trim();
            appState.set({
                successMessage: "",
                errorMessage: ""
            });
            if(jabber!==""&&jabber.length>0) {
                changeJabber.fetch({
                    data: {
                        newJabber: jabber
                    },
                    complete: function () {
                        if(changeJabber.get("message")==="success"){
                            var user = appState.get("user");
                            user.jabber=jabber;
                            appState.set({
                                successMessage: "Jabber успешно изменен",
                                user: user
                            });
                        }else {
                            appState.set({
                                errorMessage: "Изменение Jabber не удалось"
                            });
                        }
                    }
                })
            }
        },
        goToFriends: function () {
            window.location.href = window.location.origin += "/friends";
        }
    });

    var block = new Block({model: appState});

    appState.trigger("change");

    appState.bind("change:state", function () {
        var state = this.get("state");
        if (state === "main")
            controller.navigate("!/", false);
        else
            controller.navigate("!/" + state, false);
    });

    Backbone.history.start();


});