$(function () {

    var AppState = Backbone.Model.extend({
        defaults: {
            username: "",
            state: "start"
        }
    });

    var appState = new AppState();

    var UserNameModel = Backbone.Model.extend({ // Ìîäåëü ïîëüçîâàòåëÿ
        defaults: {
            "Name": ""
        }
    });

    var Family = Backbone.Collection.extend({ // Êîëëåêöèÿ ïîëüçîâàòåëåé

        model: UserNameModel,

        checkUser: function (username) { // Ïðîâåðêà ïîëüçîâàòåëÿ
            var findResult = this.find(function (user) { return user.get("Name") == username })
            return findResult != null;
        }

    });

    var MyFamily = new Family([ // Ìîÿ ñåìüÿ
        {Name: "admin" },
        { Name: "user" },
        { Name: "kekLol" },

    ]);



    var Controller = Backbone.Router.extend({
        routes: {
            "": "start", // Ïóñòîé hash-òýã
            "!/": "start", // Íà÷àëüíàÿ ñòðàíèöà
            "!/success": "success", // Áëîê óäà÷è
            "!/error": "error" // Áëîê îøèáêè
        },

        start: function () {
            appState.set({ state: "start" });
        },

        success: function () {
            appState.set({ state: "success" });
        },

        error: function () {
            appState.set({ state: "error" });
        }
    });

    var controller = new Controller(); // Ñîçäà¸ì êîíòðîëëåð


    var Block = Backbone.View.extend({
        el: $("#block"), // DOM ýëåìåíò widget'à

        templates: { // Øàáëîíû íà ðàçíîå ñîñòîÿíèå
            "start": _.template($('#start').html()),
            "success": _.template($('#success').html()),
            "error": _.template($('#error').html())
        },

        events: {
            "click input:button": "check" // Îáðàáîò÷èê êëèêà íà êíîïêå "Ïðîâåðèòü"
        },

        initialize: function () { // Ïîäïèñêà íà ñîáûòèå ìîäåëè
            this.model.bind('change', this.render, this);
        },

        check: function () {
            var username = $(this.el).find("input:text").val();
            var find = MyFamily.checkUser(username); // Ïðîâåðêà èìåíè ïîëüçîâàòåëÿ
            appState.set({ // Ñîõðàíåíèå èìåíè ïîëüçîâàòåëÿ è ñîñòîÿíèÿ
                "state": find ? "success" : "error",
                "username": username
            });
        },

        render: function () {
            var state = this.model.get("state");
            $(this.el).html(this.templates[state](this.model.toJSON()));
            return this;
        }
    });

    var block = new Block({ model: appState }); // ñîçäàäèì îáúåêò

    appState.trigger("change"); // Âûçîâåì ñîáûòèå change ó ìîäåëè

    appState.bind("change:state", function () { // ïîäïèñêà íà ñìåíó ñîñòîÿíèÿ äëÿ êîíòðîëëåðà
        var state = this.get("state");
        if (state == "start")
            controller.navigate("!/", false); // false ïîòîìó, ÷òî íàì íå íàäî
                                              // âûçûâàòü îáðàáîò÷èê ó Router
        else
            controller.navigate("!/" + state, false);
    });

    Backbone.history.start();  // Çàïóñêàåì HTML5 History push


});