$(function () {
    var Characters = Backbone.Collection.extend({
        url: "/service/getCharacters"
    });
    var User = Backbone.Model.extend({});
    var Units = Backbone.Collection.extend({
        url: "/service/getUnits"
    });
    var AppState = Backbone.Model.extend({
        defaults: {
            state: "user",
            nickError: "",
            passwordError: "",
            jabberError: "",
            user: {
                nick: "",
                password: "",
                jabber: ""
            }
        }
    });
    var Check = Backbone.Model.extend({
        url: "/service/checkNick"
    });

    var appState = new AppState();
    var units = new Units();
    var characters = new Characters();
    var check = new Check();
    var user = new User();
    appState.set({user: user});

    var Controller = Backbone.Router.extend({
        routes: {
            "": "user",
            "!/": "user",
            "!/character": "character"
        },

        character: function () {
            appState.set({state: "character"});
        },

        user: function () {
            appState.set({state: "user"});
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
            "click input#previousUnit": "prevUnit",
            "change input#count1": "changeCount1",
            "change input#count2": "changeCount2",
            "change input#count3": "changeCount3",
            "click input#plus1": "plusCount1",
            "click input#plus2": "plusCount2",
            "click input#plus3": "plusCount3",
            "click input#minus1": "minusCount1",
            "click input#minus2": "minusCount2",
            "click input#minus3": "minusCount3",
            "click input#complete": "complete"
        },

        initialize: function () {
            this.model.bind('change', this.render, this);
        },

        checkNick: function () {
            var nick = document.getElementById("nick");
            var user = this.model.get("user");
            user.nick = nick.value;
            appState.set({nickError: "", user: user});
            if (nick.value.length < 3) {
                appState.set({nickError: "Ник должен содержать минимум 3 символа"});
            } else {
                check.url = "/service/checkNick";
                check.fetch({
                    data: {
                        nick: nick.value
                    },
                    success: function () {
                        if (check.attributes.resp === "true") {
                            appState.set({nickError: "Ник занят"});
                        }
                    }
                })
            }
        },

        checkPassword: function () {
            var password = document.getElementById("password");
            var user = this.model.get("user");
            user.password = password.value;
            appState.set({passwordError: "", user: user});
            if (password.value.length < 3) {
                appState.set({passwordError: "Пароль должен содержать минимум 3 символа"});
            }
        },

        checkJabber: function () {
            var jabber = $("input#jabber").get(0);
            var user = this.model.get("user");
            user.jabber = jabber.value;
            appState.set({jabberError: "", user: user})
        },

        checkFilled: function () {
            var ret = true;
            ret = (this.model.get("jabberError").length + this.model.get("nickError").length + this.model.get("passwordError").length) === 0;
            if ($("input#nick").get(0).value.length === 0) {
                appState.set({nickError: "Поле обязательно для заполнения"});
                ret = false;
            }
            if ($("input#password").get(0).value.length === 0) {
                appState.set({passwordError: "Поле обязательно для заполнения"});
                ret = false;
            }
            if ($("input#jabber").get(0).value.length === 0) {
                appState.set({jabberError: "Поле обязательно для заполнения"});
                ret = false;
            }
            return ret;
        },

        goToCharacter: function () {
            var oterThis = this;
            var params = {
                success: function () {
                    if (oterThis.checkFilled()) {
                        appState.set({
                            state: "character",
                            charInd: 0,
                            tempChar: characters.toJSON()[0],
                            charNum: characters.length,
                            user: {
                                nick: $("input#nick").get(0).value,
                                password: $("input#password").get(0).value,
                                jabber: $("input#jabber").get(0).value
                            }
                        })
                    }
                }
            };
            characters.fetch(params);
        },

        goToArmy: function () {
            var pastUser = this.model.get("user");
            var outerThis = this;
            var params = {
                success: function () {
                    var unitNumber = units.length;
                    var unitCounters = [];
                    var shownUnitCounters = [0, 0, 0];
                    for (var i = 0; i < unitNumber; i++) {
                        unitCounters.push(0);
                    }
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
                        unitCount: unitCounters,
                        shownUnits: [units.at(0).toJSON(), units.at(1).toJSON(), units.at(2).toJSON()],
                        shownUnitCount: shownUnitCounters,
                        power: 0
                    })
                }
            };
            units.fetch(params);
        },

        render: function () {
            var state = this.model.get("state");
            $(this.el).html(this.templates[state](this.model.toJSON()));
            return this;

        },

        nextChar: function () {
            var ind = this.model.get("charInd");
            if (ind + 1 < this.model.get("charNum")) {
                ind++;
                appState.set({
                    charInd: ind,
                    tempChar: characters.at(ind).toJSON()
                })
            }
        },

        prevChar: function () {
            var ind = this.model.get("charInd");
            if (ind > 0) {
                ind--;
                appState.set({
                    charInd: ind,
                    tempChar: characters.at(ind).toJSON()
                })
            }
        },

        calculatePower: function () {
            var pow = 0;
            for (var i = 0; i < this.model.get("unitNum"); i++) {
                pow += units.at(i).get("power") * this.model.get("unitCount")[i];
            }
            appState.set({
                power: pow
            });
        },

        nextUnit: function () {
            var ind = this.model.get("unitInd");
            var shown = this.model.get("shownUnits");
            var counters = this.model.get("unitCount");
            var shownCounters = this.model.get("shownUnitCount");
            var length = shown.length;
            if (ind + length < this.model.get("unitNum")) {
                for (var i = 0; i < length - 1; i++) {
                    shown[i] = shown[i + 1];
                    shownCounters[i] = shownCounters[i + 1];
                }
                shown[length - 1] = units.at(ind + length).toJSON();
                shownCounters[length - 1] = counters[ind + length];
                ind++;
                appState.set({
                    unitInd: ind,
                    shownUnits: shown,
                    shownUnitCount: shownCounters
                })
            }
        },

        prevUnit: function () {
            var ind = this.model.get("unitInd");
            var shown = this.model.get("shownUnits");
            var counters = this.model.get("unitCount");
            var shownCounters = this.model.get("shownUnitCount");
            var length = shown.length;
            if (ind > 0) {
                for (var i = length - 2; i >= 0; i--) {
                    shown[i + 1] = shown[i];
                    shownCounters[i + 1] = shownCounters[i];
                }
                ind--;
                shown[0] = units.at(ind).toJSON();
                shownCounters[0] = counters[ind];
                appState.set({
                    unitInd: ind,
                    shownUnits: shown,
                    shownUnitCount: shownCounters
                })
            }
        },

        changeCount: function (index, value) {
            var unitCounters = this.model.get("unitCount");
            unitCounters[index + this.model.get("unitInd")] = value;
            var shownCounters = this.model.get("shownUnitCount");
            shownCounters[index] = value;
            appState.set({
                shownUnitCount: shownCounters,
                unitCount: unitCounters
            });
            this.calculatePower()
        },

        //3 разных варианта это артефакт. особого смысла нет.
        changeCount1: function () {
            var index = 0;
            var value = $("input#count1").val();
            this.changeCount(index, value);
        },

        changeCount2: function () {
            var index = 1;
            this.changeCount(index, $("input#count2").val());
        },

        changeCount3: function () {
            var index = 2;
            var value = document.getElementById("count3").value;
            this.changeCount(index, value);
        },
        plusCount1: function () {
            if (this.model.get("power") <= 100)
                this.changeCount(0, Number($("input#count1").val()) + 1);
        },
        plusCount2: function () {
            if (this.model.get("power") <= 100)
                this.changeCount(1, Number($("input#count2").val()) + 1);
        },
        plusCount3: function () {
            if (this.model.get("power") <= 100)
                this.changeCount(2, Number($("input#count3").val()) + 1);
        },
        minusCount1: function () {
            var value = $("input#count1").val();
            if (value > 0)
                this.changeCount(0, value - 1);
        },
        minusCount2: function () {
            var value = $("input#count2").val();
            if (value > 0)
                this.changeCount(1, value - 1);
        },
        minusCount3: function () {
            var value = $("input#count3").val();
            if (value > 0)
                this.changeCount(2, value - 1);
        },
        complete: function () {
            if (this.model.get("power") <= 100) {
                check.url = "/service/complete";//используем ту же модель, что и для проверки ника, чтобы не создавать новую
                var user = this.model.get("user");
                var unitIDs = [];
                for (var i = 0; i < units.length; i++) {
                    unitIDs.push(units.at(i).get("id"))
                }
                var params = {
                    data: {
                        nick: user.nick,
                        password: user.password,
                        jabber: user.jabber,
                        charName: user.character.name,
                        unitIDs: unitIDs.toString(),
                        unitCount: this.model.get("unitCount").toString()
                    },
                    success: function () {
                        window.location.href = window.location.origin += "/?success";
                    },
                    error: function () {
                        appState.set({
                            submitError: "Произошла ошибка при обработке данных, попробуйте позже"
                        })
                    }
                };
                check.fetch(params);
            }
        }
    });
    var block = new Registration({model: appState});

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