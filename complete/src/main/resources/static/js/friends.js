$(function () {

    var FriendNames = Backbone.Collection.extend({
            url: "/secure/getFriends"
        }
    );

    var FriendRequests = Backbone.Collection.extend({
            url: "/secure/getFriendRequests"
        }
    );

    var Respond = Backbone.Model.extend({
        url: "/secure/respondFriendRequest"
    });

    var Delete = Backbone.Model.extend({
        url: "/secure/deleteFriend"
    });

    var User = Backbone.Model.extend({
        url: "/secure/getUser"
    });

    var Message = Backbone.Model.extend({
        url: "/secure/requestFriendship"
    });

    var Restore = Backbone.Model.extend({
        url: "/secure/restoreFriend"
    });

    var restore = new Restore();
    var friendNames = new FriendNames();
    var friendRequests = new FriendRequests();
    var user = new User();
    var message = new Message();
    var respond = new Respond();
    var del = new Delete();

    var AppState = Backbone.Model.extend({
        defaults: {
            state: "none",
            friends: [{name: "testFriend", online: true, isFriend: true}],//костыль, лень делать нормально
            user: undefined,
            requests: [],
            successMessage: ""
        }
    });
    refreshFriends();

    var appState = new AppState();

    var Controller = Backbone.Router.extend({
        routes: {
            "": "none",
            "!/": "none",
            "!/player": "player"
        },

        none: function () {
            appState.set({state: "none"});
        },

        player: function () {
            appState.set({state: "player"});
        }
    });

    var controller = new Controller();


    var Block = Backbone.View.extend({
        el: $("#block"),

        templates: {
            "none": _.template($('#none').html()),
            "player": _.template($('#player').html())
        },

        events: {
            "click input#searchButton": "search",
            "click input#invite": "showTextArea",
            "click input#submit": "sendRequest",
            "click input.respond": "respondFriend",
            "click input#delete": "deleteFriend",
            "click td.lefted": "findFriend",
            "click input#restore": "restoreFriend"
        },

        initialize: function () {
            this.model.bind('change', this.render, this);

        },

        getUser: function (nick) {
            if (nick !== "" && nick.length > 0) {
                user.fetch({
                    data: {
                        name: nick
                    },
                    success: function () {
                        appState.set({
                            user: user.toJSON()
                        })
                    },
                    error: function () {
                        appState.set({
                            user: null
                        })
                    }
                })
            }
        },

        render: function () {
            var state = this.model.get("state");
            $(this.el).html(this.templates[state](this.model.toJSON()));
            return this;
        },

        search: function () {
            $("div#messageSender").hide();
            var nick = $("input#searchField").get(0).value;
            nick = nick.trim();
            appState.set({
                successMessage: ""
            });
            this.getUser(nick);
        },

        findFriend: function (event) {
            $("div#messageSender").hide();
            //console.log(event.target);
            var nick = event.target.id;
            appState.set({
                successMessage: ""
            });
            this.getUser(nick);
        },

        showTextArea: function () {
            $("div#messageSender").show();
        },

        sendRequest: function () {
            var mess = $("textarea#message").get(0).value;
            var receiver = this.model.get("user").name;
            var outThis = this;
            message.fetch({
                data: {
                    name: receiver,
                    message: mess
                },
                complete: function () {
                    if(message.get("message")==="success") {
                        refreshFriends();
                        outThis.getUser(receiver);
                        appState.set({
                            successMessage: "Запрос успешно отправлен"
                        })
                    }
                }
            })
        },
        respondFriend: function (event) {
            var id = event.target.id;
            var type = id.substr(0, 3);
            var receiver = id.substr(3);
            var resp = "refuse";
            var outThis = this;
            if (type === "yes") {
                resp = "apply"
            }
            respond.fetch({
                data: {
                    name: receiver,
                    respond: resp
                },
                complete: function () {
                    if(respond.get("message")==="success") {
                        var user = outThis.model.get("user");
                        if(user!==undefined&&user!==null) {
                            outThis.getUser(user.name);
                        }
                        refreshFriends();
                        appState.set({
                            successMessage: "Ответ на запрос принят"
                        })
                    }
                }
            });
        },
        deleteFriend: function () {
            var nick=this.model.get("user").name;
            var outThis = this;
            del.fetch({
                data:{
                    name: nick
                },
                complete: function () {
                    if(del.get("message")==="success") {
                        refreshFriends();
                        outThis.getUser(nick);
                        appState.set({
                            successMessage: "Пользователь успешно удален"
                        })
                    }
                }
            })
        },
        restoreFriend: function () {
            var nick = this.model.get("user").name;
            var outThis = this;
            restore.fetch({
                data:{
                    name: nick
                },
                complete: function () {
                    if(restore.get("message")==="success") {
                        refreshFriends();
                        outThis.getUser(nick);
                        appState.set({
                            successMessage: "Пользователь успешно восстановлен",
                            user: undefined
                        })
                    }
                }
            })
        }
    });

    var block = new Block({model: appState});

    appState.trigger("change");

    appState.bind("change:state", function () {
        var state = this.get("state");
        if (state == "none")
            controller.navigate("!/", false);
        else
            controller.navigate("!/" + state, false);
    });

    Backbone.history.start();

    function refreshFriends() {
        friendNames.fetch({
            success: function () {
                appState.set({
                    friends: friendNames.toJSON()
                });
            }
        });
        friendRequests.fetch({
            success: function () {
                appState.set({
                    requests: friendRequests.toJSON()
                })
            }
        })
    }

    window.setInterval(refreshFriends, 10000)
});