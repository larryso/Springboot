
var mui = function(a, b) {
    var c = /complete|loaded|interactive/
        , d = /^#([\w-]+)$/
        , e = /^\.([\w-]+)$/
        , f = /^[\w-]+$/
        , g = /translate(?:3d)?\((.+?)\)/
        , h = /matrix(3d)?\((.+?)\)/
        , i = function(b, c) {
        if (c = c || a,
                !b)
            return j();
        if ("object" == typeof b)
            return i.isArrayLike(b) ? j(i.slice.call(b), null) : j([b], null);
        if ("function" == typeof b)
            return i.ready(b);
        if ("string" == typeof b)
            try {
                if (b = b.trim(),
                        d.test(b)) {
                    var e = a.getElementById(RegExp.$1);
                    return j(e ? [e] : [])
                }
                return j(i.qsa(b, c), b)
            } catch (f) {}
        return j()
    }
        , j = function(a, b) {
        return a = a || [],
            Object.setPrototypeOf(a, i.fn),
            a.selector = b || "",
            a
    };
    i.uuid = 0,
        i.data = {},
        i.extend = function() {
            var a, c, d, e, f, g, h = arguments[0] || {}, j = 1, k = arguments.length, l = !1;
            for ("boolean" == typeof h && (l = h,
                h = arguments[j] || {},
                j++),
                 "object" == typeof h || i.isFunction(h) || (h = {}),
                 j === k && (h = this,
                     j--); k > j; j++)
                if (null != (a = arguments[j]))
                    for (c in a)
                        d = h[c],
                            e = a[c],
                        h !== e && (l && e && (i.isPlainObject(e) || (f = i.isArray(e))) ? (f ? (f = !1,
                            g = d && i.isArray(d) ? d : []) : g = d && i.isPlainObject(d) ? d : {},
                            h[c] = i.extend(l, g, e)) : e !== b && (h[c] = e));
            return h
        }
        ,
        i.noop = function() {}
        ,
        i.slice = [].slice,
        i.filter = [].filter,
        i.type = function(a) {
            return null == a ? String(a) : k[{}.toString.call(a)] || "object"
        }
        ,
        i.isArray = Array.isArray || function(a) {
                return a instanceof Array
            }
        ,
        i.isArrayLike = function(a) {
            var b = !!a && "length"in a && a.length
                , c = i.type(a);
            return "function" === c || i.isWindow(a) ? !1 : "array" === c || 0 === b || "number" == typeof b && b > 0 && b - 1 in a
        }
        ,
        i.isWindow = function(a) {
            return null != a && a === a.window
        }
        ,
        i.isObject = function(a) {
            return "object" === i.type(a)
        }
        ,
        i.isPlainObject = function(a) {
            return i.isObject(a) && !i.isWindow(a) && Object.getPrototypeOf(a) === Object.prototype
        }
        ,
        i.isEmptyObject = function(a) {
            for (var c in a)
                if (c !== b)
                    return !1;
            return !0
        }
        ,
        i.isFunction = function(a) {
            return "function" === i.type(a)
        }
        ,
        i.qsa = function(b, c) {
            return c = c || a,
                i.slice.call(e.test(b) ? c.getElementsByClassName(RegExp.$1) : f.test(b) ? c.getElementsByTagName(b) : c.querySelectorAll(b))
        }
        ,
        i.ready = function(b) {
            return c.test(a.readyState) ? b(i) : a.addEventListener("DOMContentLoaded", function() {
                b(i)
            }, !1),
                this
        }
        ,
        i.buffer = function(a, b, c) {
            function d() {
                e && (e.cancel(),
                    e = 0),
                    f = i.now(),
                    a.apply(c || this, arguments),
                    g = i.now()
            }
            var e, f = 0, g = 0, b = b || 150;
            return i.extend(function() {
                !f || g >= f && i.now() - g > b || f > g && i.now() - f > 8 * b ? d() : (e && e.cancel(),
                    e = i.later(d, b, null, arguments))
            }, {
                stop: function() {
                    e && (e.cancel(),
                        e = 0)
                }
            })
        }
        ,
        i.each = function(a, b, c) {
            if (!a)
                return this;
            if ("number" == typeof a.length)
                [].every.call(a, function(a, c) {
                    return b.call(a, c, a) !== !1
                });
            else
                for (var d in a)
                    if (c) {
                        if (a.hasOwnProperty(d) && b.call(a[d], d, a[d]) === !1)
                            return a
                    } else if (b.call(a[d], d, a[d]) === !1)
                        return a;
            return this
        }
        ,
        i.focus = function(a) {
            i.os.ios ? setTimeout(function() {
                a.focus()
            }, 10) : a.focus()
        }
        ,
        i.trigger = function(a, b, c) {
            return a.dispatchEvent(new CustomEvent(b,{
                detail: c,
                bubbles: !0,
                cancelable: !0
            })),
                this
        }
        ,
        i.getStyles = function(a, b) {
            var c = a.ownerDocument.defaultView.getComputedStyle(a, null);
            return b ? c.getPropertyValue(b) || c[b] : c
        }
        ,
        i.parseTranslate = function(a, b) {
            var c = a.match(g || "");
            return c && c[1] || (c = ["", "0,0,0"]),
                c = c[1].split(","),
                c = {
                    x: parseFloat(c[0]),
                    y: parseFloat(c[1]),
                    z: parseFloat(c[2])
                },
                b && c.hasOwnProperty(b) ? c[b] : c
        }
        ,
        i.parseTranslateMatrix = function(a, b) {
            var c = a.match(h)
                , d = c && c[1];
            c ? (c = c[2].split(","),
                "3d" === d ? c = c.slice(12, 15) : (c.push(0),
                    c = c.slice(4, 7))) : c = [0, 0, 0];
            var e = {
                x: parseFloat(c[0]),
                y: parseFloat(c[1]),
                z: parseFloat(c[2])
            };
            return b && e.hasOwnProperty(b) ? e[b] : e
        }
        ,
        i.hooks = {},
        i.addAction = function(a, b) {
            var c = i.hooks[a];
            return c || (c = []),
                b.index = b.index || 1e3,
                c.push(b),
                c.sort(function(a, b) {
                    return a.index - b.index
                }),
                i.hooks[a] = c,
                i.hooks[a]
        }
        ,
        i.doAction = function(a, b) {
            i.isFunction(b) ? i.each(i.hooks[a], b) : i.each(i.hooks[a], function(a, b) {
                return !b.handle()
            })
        }
        ,
        i.later = function(a, b, c, d) {
            b = b || 0;
            var e, f, g = a, h = d;
            return "string" == typeof a && (g = c[a]),
                e = function() {
                    g.apply(c, i.isArray(h) ? h : [h])
                }
                ,
                f = setTimeout(e, b),
                {
                    id: f,
                    cancel: function() {
                        clearTimeout(f)
                    }
                }
        }
        ,
        i.now = Date.now || function() {
                return +new Date
            }
    ;
    var k = {};
    return i.each(["Boolean", "Number", "String", "Function", "Array", "Date", "RegExp", "Object", "Error"], function(a, b) {
        k["[object " + b + "]"] = b.toLowerCase()
    }),
    window.JSON && (i.parseJSON = JSON.parse),
        i.fn = {
            each: function(a) {
                return [].every.call(this, function(b, c) {
                    return a.call(b, c, b) !== !1
                }),
                    this
            }
        },
    "function" == typeof define && define.amd && define("mui", [], function() {
        return i
    }),
        i
}(document);
!function(a, b) {
    function c(c) {
        this.os = {};
        var d = [function() {
            var a = c.match(/(MicroMessenger)\/([\d\.]+)/i);
            return a && (this.os.wechat = {
                version: a[2].replace(/_/g, ".")
            }),
                !1
        }
            , function() {
                var a = c.match(/(Android);?[\s\/]+([\d.]+)?/);
                return a && (this.os.android = !0,
                    this.os.version = a[2],
                    this.os.isBadAndroid = !/Chrome\/\d/.test(b.navigator.appVersion)),
                this.os.android === !0
            }
            , function() {
                var a = c.match(/(iPhone\sOS)\s([\d_]+)/);
                if (a)
                    this.os.ios = this.os.iphone = !0,
                        this.os.version = a[2].replace(/_/g, ".");
                else {
                    var b = c.match(/(iPad).*OS\s([\d_]+)/);
                    b && (this.os.ios = this.os.ipad = !0,
                        this.os.version = b[2].replace(/_/g, "."))
                }
                return this.os.ios === !0
            }
        ];
        [].every.call(d, function(b) {
            return !b.call(a)
        })
    }
    c.call(a, navigator.userAgent)
}(mui, window),
    function(a, b) {
        function c(c) {
            this.os = this.os || {};
            var d = c.match(/Html5Plus/i);
            d && (this.os.plus = !0,
                a(function() {
                    b.body.classList.add("mui-plus")
                }),
            c.match(/StreamApp/i) && (this.os.stream = !0,
                a(function() {
                    b.body.classList.add("mui-plus-stream")
                })))
        }
        c.call(a, navigator.userAgent)
    }(mui, document),
    function(a) {
        "ontouchstart"in window ? (a.isTouchable = !0,
            a.EVENT_START = "touchstart",
            a.EVENT_MOVE = "touchmove",
            a.EVENT_END = "touchend") : (a.isTouchable = !1,
            a.EVENT_START = "mousedown",
            a.EVENT_MOVE = "mousemove",
            a.EVENT_END = "mouseup"),
            a.EVENT_CANCEL = "touchcancel",
            a.EVENT_CLICK = "click";
        var b = 1
            , c = {}
            , d = {
            preventDefault: "isDefaultPrevented",
            stopImmediatePropagation: "isImmediatePropagationStopped",
            stopPropagation: "isPropagationStopped"
        }
            , e = function() {
            return !0
        }
            , f = function() {
            return !1
        }
            , g = function(b, c) {
            return b.detail ? b.detail.currentTarget = c : b.detail = {
                currentTarget: c
            },
                a.each(d, function(a, c) {
                    var d = b[a];
                    b[a] = function() {
                        return this[c] = e,
                        d && d.apply(b, arguments)
                    }
                        ,
                        b[c] = f
                }, !0),
                b
        }
            , h = function(a) {
            return a && (a._mid || (a._mid = b++))
        }
            , i = {}
            , j = function(b, d, e, f) {
            return function(e) {
                for (var f = c[b._mid][d], h = [], i = e.target, j = {}; i && i !== document && i !== b && (!~["click", "tap", "doubletap", "longtap", "hold"].indexOf(d) || !i.disabled && !i.classList.contains("mui-disabled")); i = i.parentNode) {
                    var k = {};
                    a.each(f, function(c, d) {
                        j[c] || (j[c] = a.qsa(c, b)),
                        j[c] && ~j[c].indexOf(i) && (k[c] || (k[c] = d))
                    }, !0),
                    a.isEmptyObject(k) || h.push({
                        element: i,
                        handlers: k
                    })
                }
                j = null,
                    e = g(e),
                    a.each(h, function(b, c) {
                        i = c.element;
                        var f = i.tagName;
                        return "tap" === d && "INPUT" !== f && "TEXTAREA" !== f && "SELECT" !== f && (e.preventDefault(),
                        e.detail && e.detail.gesture && e.detail.gesture.preventDefault()),
                            a.each(c.handlers, function(b, c) {
                                a.each(c, function(a, b) {
                                    b.call(i, e) === !1 && (e.preventDefault(),
                                        e.stopPropagation())
                                }, !0)
                            }, !0),
                            e.isPropagationStopped() ? !1 : void 0
                    }, !0)
            }
        }
            , k = function(a, b) {
            var c = i[h(a)]
                , d = [];
            if (c) {
                if (d = [],
                        b) {
                    var e = function(a) {
                        return a.type === b
                    };
                    return c.filter(e)
                }
                d = c
            }
            return d
        }
            , l = /^(INPUT|TEXTAREA|BUTTON|SELECT)$/;
        a.fn.on = function(b, d, e) {
            return this.each(function() {
                var f = this;
                h(f),
                    h(e);
                var g = !1
                    , k = c[f._mid] || (c[f._mid] = {})
                    , m = k[b] || (k[b] = {});
                a.isEmptyObject(m) && (g = !0);
                var n = m[d] || (m[d] = []);
                if (n.push(e),
                        g) {
                    var o = i[h(f)];
                    o || (o = []);
                    var p = j(f, b, d, e);
                    o.push(p),
                        p.i = o.length - 1,
                        p.type = b,
                        i[h(f)] = o,
                        f.addEventListener(b, p),
                    "tap" === b && f.addEventListener("click", function(a) {
                        if (a.target) {
                            var b = a.target.tagName;
                            if (!l.test(b))
                                if ("A" === b) {
                                    var c = a.target.href;
                                    c && ~c.indexOf("tel:") || a.preventDefault()
                                } else
                                    a.preventDefault()
                        }
                    })
                }
            })
        }
            ,
            a.fn.off = function(b, d, e) {
                return this.each(function() {
                    var f = h(this);
                    if (b)
                        if (d)
                            if (e) {
                                var g = c[f] && c[f][b] && c[f][b][d];
                                a.each(g, function(a, b) {
                                    return h(b) === h(e) ? (g.splice(a, 1),
                                        !1) : void 0
                                }, !0)
                            } else
                                c[f] && c[f][b] && delete c[f][b][d];
                        else
                            c[f] && delete c[f][b];
                    else
                        c[f] && delete c[f];
                    c[f] ? (!c[f][b] || a.isEmptyObject(c[f][b])) && k(this, b).forEach(function(a) {
                            this.removeEventListener(a.type, a),
                                delete i[f][a.i]
                        }
                            .bind(this)) : k(this).forEach(function(a) {
                        this.removeEventListener(a.type, a),
                            delete i[f][a.i]
                    }
                        .bind(this))
                })
            }
    }(mui),
    function(a, b, c) {
        a.targets = {},
            a.targetHandles = [],
            a.registerTarget = function(b) {
                return b.index = b.index || 1e3,
                    a.targetHandles.push(b),
                    a.targetHandles.sort(function(a, b) {
                        return a.index - b.index
                    }),
                    a.targetHandles
            }
            ,
            b.addEventListener(a.EVENT_START, function(b) {
                for (var d = b.target, e = {}; d && d !== c; d = d.parentNode) {
                    var f = !1;
                    if (a.each(a.targetHandles, function(c, g) {
                            var h = g.name;
                            f || e[h] || !g.hasOwnProperty("handle") ? e[h] || g.isReset !== !1 && (a.targets[h] = !1) : (a.targets[h] = g.handle(b, d),
                            a.targets[h] && (e[h] = !0,
                            g.isContinue !== !0 && (f = !0)))
                        }),
                            f)
                        break
                }
            }),
            b.addEventListener("click", function(b) {
                for (var d = b.target, e = !1; d && d !== c && ("A" !== d.tagName || (a.each(a.targetHandles, function(a, c) {
                    c.name;
                    return c.hasOwnProperty("handle") && c.handle(b, d) ? (e = !0,
                        b.preventDefault(),
                        !1) : void 0
                }),
                    !e)); d = d.parentNode)
                    ;
            })
    }(mui, window, document),
    function(a) {
        String.prototype.trim === a && (String.prototype.trim = function() {
                return this.replace(/^\s+|\s+$/g, "")
            }
        ),
            Object.setPrototypeOf = Object.setPrototypeOf || function(a, b) {
                    return a.__proto__ = b,
                        a
                }
    }(),
    function() {
        function a(a, b) {
            b = b || {
                    bubbles: !1,
                    cancelable: !1,
                    detail: void 0
                };
            var c = document.createEvent("Events")
                , d = !0;
            for (var e in b)
                "bubbles" === e ? d = !!b[e] : c[e] = b[e];
            return c.initEvent(a, d, !0),
                c
        }
        "undefined" == typeof window.CustomEvent && (a.prototype = window.Event.prototype,
            window.CustomEvent = a)
    }(),
    Function.prototype.bind = Function.prototype.bind || function(a) {
            var b = Array.prototype.splice.call(arguments, 1)
                , c = this
                , d = function() {
                var e = b.concat(Array.prototype.splice.call(arguments, 0));
                return this instanceof d ? void c.apply(this, e) : c.apply(a, e)
            };
            return d.prototype = c.prototype,
                d
        }
    ,
    function(a) {
        "classList"in a.documentElement || !Object.defineProperty || "undefined" == typeof HTMLElement || Object.defineProperty(HTMLElement.prototype, "classList", {
            get: function() {
                function a(a) {
                    return function(c) {
                        var d = b.className.split(/\s+/)
                            , e = d.indexOf(c);
                        a(d, e, c),
                            b.className = d.join(" ")
                    }
                }
                var b = this
                    , c = {
                    add: a(function(a, b, c) {
                        ~b || a.push(c)
                    }),
                    remove: a(function(a, b) {
                        ~b && a.splice(b, 1)
                    }),
                    toggle: a(function(a, b, c) {
                        ~b ? a.splice(b, 1) : a.push(c)
                    }),
                    contains: function(a) {
                        return !!~b.className.split(/\s+/).indexOf(a)
                    },
                    item: function(a) {
                        return b.className.split(/\s+/)[a] || null
                    }
                };
                return Object.defineProperty(c, "length", {
                    get: function() {
                        return b.className.split(/\s+/).length
                    }
                }),
                    c
            }
        })
    }(document),
    function(a) {
        if (!a.requestAnimationFrame) {
            var b = 0;
            a.requestAnimationFrame = a.webkitRequestAnimationFrame || function(c, d) {
                    var e = (new Date).getTime()
                        , f = Math.max(0, 16.7 - (e - b))
                        , g = a.setTimeout(function() {
                        c(e + f)
                    }, f);
                    return b = e + f,
                        g
                }
                ,
                a.cancelAnimationFrame = a.webkitCancelAnimationFrame || a.webkitCancelRequestAnimationFrame || function(a) {
                        clearTimeout(a)
                    }
        }
    }(window),
    function(a, b, c) {
        if ((a.os.android || a.os.ios) && !b.FastClick) {
            var d = function(a, b) {
                return "LABEL" === b.tagName && b.parentNode && (b = b.parentNode.querySelector("input")),
                    !b || "radio" !== b.type && "checkbox" !== b.type || b.disabled ? !1 : b
            };
            a.registerTarget({
                name: c,
                index: 40,
                handle: d,
                target: !1
            });
            var e = function(c) {
                var d = a.targets.click;
                if (d) {
                    var e, f;
                    document.activeElement && document.activeElement !== d && document.activeElement.blur(),
                        f = c.detail.gesture.changedTouches[0],
                        e = document.createEvent("MouseEvents"),
                        e.initMouseEvent("click", !0, !0, b, 1, f.screenX, f.screenY, f.clientX, f.clientY, !1, !1, !1, !1, 0, null),
                        e.forwardedTouchEvent = !0,
                        d.dispatchEvent(e),
                    c.detail && c.detail.gesture.preventDefault()
                }
            };
            b.addEventListener("tap", e),
                b.addEventListener("doubletap", e),
                b.addEventListener("click", function(b) {
                    return a.targets.click && !b.forwardedTouchEvent ? (b.stopImmediatePropagation ? b.stopImmediatePropagation() : b.propagationStopped = !0,
                        b.stopPropagation(),
                        b.preventDefault(),
                        !1) : void 0
                }, !0)
        }
    }(mui, window, "click"),
    function(a, b) {
        a(function() {
            if (a.os.ios) {
                var c = "mui-focusin"
                    , d = "mui-bar-tab"
                    , e = "mui-bar-footer"
                    , f = "mui-bar-footer-secondary"
                    , g = "mui-bar-footer-secondary-tab";
                b.addEventListener("focusin", function(h) {
                    if (!(a.os.plus && window.plus && plus.webview.currentWebview().children().length > 0)) {
                        var i = h.target;
                        if (i.tagName && ("TEXTAREA" === i.tagName || "INPUT" === i.tagName && ("text" === i.type || "search" === i.type || "number" === i.type))) {
                            if (i.disabled || i.readOnly)
                                return;
                            b.body.classList.add(c);
                            for (var j = !1; i && i !== b; i = i.parentNode) {
                                var k = i.classList;
                                if (k && k.contains(d) || k.contains(e) || k.contains(f) || k.contains(g)) {
                                    j = !0;
                                    break
                                }
                            }
                            if (j) {
                                var l = b.body.scrollHeight
                                    , m = b.body.scrollLeft;
                                setTimeout(function() {
                                    window.scrollTo(m, l)
                                }, 20)
                            }
                        }
                    }
                }),
                    b.addEventListener("focusout", function(a) {
                        var d = b.body.classList;
                        d.contains(c) && (d.remove(c),
                            setTimeout(function() {
                                window.scrollTo(b.body.scrollLeft, b.body.scrollTop)
                            }, 20))
                    })
            }
        })
    }(mui, document),
    function(a) {
        a.namespace = "mui",
            a.classNamePrefix = a.namespace + "-",
            a.classSelectorPrefix = "." + a.classNamePrefix,
            a.className = function(b) {
                return a.classNamePrefix + b
            }
            ,
            a.classSelector = function(b) {
                return b.replace(/\./g, a.classSelectorPrefix)
            }
            ,
            a.eventName = function(b, c) {
                return b + (a.namespace ? "." + a.namespace : "") + (c ? "." + c : "")
            }
    }(mui),
    function(a, b) {
        a.gestures = {
            session: {}
        },
            a.preventDefault = function(a) {
                a.preventDefault()
            }
            ,
            a.stopPropagation = function(a) {
                a.stopPropagation()
            }
            ,
            a.addGesture = function(b) {
                return a.addAction("gestures", b)
            }
        ;
        var c = Math.round
            , d = Math.abs
            , e = Math.sqrt
            , f = (Math.atan,
            Math.atan2)
            , g = function(a, b, c) {
            c || (c = ["x", "y"]);
            var d = b[c[0]] - a[c[0]]
                , f = b[c[1]] - a[c[1]];
            return e(d * d + f * f)
        }
            , h = function(a, b) {
            if (a.length >= 2 && b.length >= 2) {
                var c = ["pageX", "pageY"];
                return g(b[1], b[0], c) / g(a[1], a[0], c)
            }
            return 1
        }
            , i = function(a, b, c) {
            c || (c = ["x", "y"]);
            var d = b[c[0]] - a[c[0]]
                , e = b[c[1]] - a[c[1]];
            return 180 * f(e, d) / Math.PI
        }
            , j = function(a, b) {
            return a === b ? "" : d(a) >= d(b) ? a > 0 ? "left" : "right" : b > 0 ? "up" : "down"
        }
            , k = function(a, b) {
            var c = ["pageX", "pageY"];
            return i(b[1], b[0], c) - i(a[1], a[0], c)
        }
            , l = function(a, b, c) {
            return {
                x: b / a || 0,
                y: c / a || 0
            }
        }
            , m = function(b, c) {
            a.gestures.stoped || a.doAction("gestures", function(d, e) {
                a.gestures.stoped || a.options.gestureConfig[e.name] !== !1 && e.handle(b, c)
            })
        }
            , n = function(a, b) {
            for (; a; ) {
                if (a == b)
                    return !0;
                a = a.parentNode
            }
            return !1
        }
            , o = function(a, b, c) {
            for (var d = [], e = [], f = 0; f < a.length; ) {
                var g = b ? a[f][b] : a[f];
                e.indexOf(g) < 0 && d.push(a[f]),
                    e[f] = g,
                    f++
            }
            return c && (d = b ? d.sort(function(a, c) {
                return a[b] > c[b]
            }) : d.sort()),
                d
        }
            , p = function(a) {
            var b = a.length;
            if (1 === b)
                return {
                    x: c(a[0].pageX),
                    y: c(a[0].pageY)
                };
            for (var d = 0, e = 0, f = 0; b > f; )
                d += a[f].pageX,
                    e += a[f].pageY,
                    f++;
            return {
                x: c(d / b),
                y: c(e / b)
            }
        }
            , q = function() {
            return a.options.gestureConfig.pinch
        }
            , r = function(b) {
            for (var d = [], e = 0; e < b.touches.length; )
                d[e] = {
                    pageX: c(b.touches[e].pageX),
                    pageY: c(b.touches[e].pageY)
                },
                    e++;
            return {
                timestamp: a.now(),
                gesture: b.gesture,
                touches: d,
                center: p(b.touches),
                deltaX: b.deltaX,
                deltaY: b.deltaY
            }
        }
            , s = function(b) {
            var c = a.gestures.session
                , d = b.center
                , e = c.offsetDelta || {}
                , f = c.prevDelta || {}
                , g = c.prevTouch || {};
            (b.gesture.type === a.EVENT_START || b.gesture.type === a.EVENT_END) && (f = c.prevDelta = {
                x: g.deltaX || 0,
                y: g.deltaY || 0
            },
                e = c.offsetDelta = {
                    x: d.x,
                    y: d.y
                }),
                b.deltaX = f.x + (d.x - e.x),
                b.deltaY = f.y + (d.y - e.y)
        }
            , t = function(b) {
            var c = a.gestures.session
                , d = b.touches
                , e = d.length;
            c.firstTouch || (c.firstTouch = r(b)),
                q() && e > 1 && !c.firstMultiTouch ? c.firstMultiTouch = r(b) : 1 === e && (c.firstMultiTouch = !1);
            var f = c.firstTouch
                , l = c.firstMultiTouch
                , m = l ? l.center : f.center
                , n = b.center = p(d);
            b.timestamp = a.now(),
                b.deltaTime = b.timestamp - f.timestamp,
                b.angle = i(m, n),
                b.distance = g(m, n),
                s(b),
                b.offsetDirection = j(b.deltaX, b.deltaY),
                b.scale = l ? h(l.touches, d) : 1,
                b.rotation = l ? k(l.touches, d) : 0,
                v(b)
        }
            , u = 25
            , v = function(b) {
            var c, e, f, g, h = a.gestures.session, i = h.lastInterval || b, k = b.timestamp - i.timestamp;
            if (b.gesture.type != a.EVENT_CANCEL && (k > u || void 0 === i.velocity)) {
                var m = i.deltaX - b.deltaX
                    , n = i.deltaY - b.deltaY
                    , o = l(k, m, n);
                e = o.x,
                    f = o.y,
                    c = d(o.x) > d(o.y) ? o.x : o.y,
                    g = j(m, n) || i.direction,
                    h.lastInterval = b
            } else
                c = i.velocity,
                    e = i.velocityX,
                    f = i.velocityY,
                    g = i.direction;
            b.velocity = c,
                b.velocityX = e,
                b.velocityY = f,
                b.direction = g
        }
            , w = {}
            , x = function(a) {
            for (var b = 0; b < a.length; b++)
                !a.identifier && (a.identifier = 0);
            return a
        }
            , y = function(b, c) {
            var d = x(a.slice.call(b.touches || [b]))
                , e = b.type
                , f = []
                , g = [];
            if (e !== a.EVENT_START && e !== a.EVENT_MOVE || 1 !== d.length) {
                var h = 0
                    , f = []
                    , g = []
                    , i = x(a.slice.call(b.changedTouches || [b]));
                c.target = b.target;
                var j = a.gestures.session.target || b.target;
                if (f = d.filter(function(a) {
                        return n(a.target, j)
                    }),
                    e === a.EVENT_START)
                    for (h = 0; h < f.length; )
                        w[f[h].identifier] = !0,
                            h++;
                for (h = 0; h < i.length; )
                    w[i[h].identifier] && g.push(i[h]),
                    (e === a.EVENT_END || e === a.EVENT_CANCEL) && delete w[i[h].identifier],
                        h++;
                if (!g.length)
                    return !1
            } else
                w[d[0].identifier] = !0,
                    f = d,
                    g = d,
                    c.target = b.target;
            f = o(f.concat(g), "identifier", !0);
            var k = f.length
                , l = g.length;
            return e === a.EVENT_START && k - l === 0 && (c.isFirst = !0,
                a.gestures.touch = a.gestures.session = {
                    target: b.target
                }),
                c.isFinal = (e === a.EVENT_END || e === a.EVENT_CANCEL) && k - l === 0,
                c.touches = f,
                c.changedTouches = g,
                !0
        }
            , z = function(b) {
            var c = {
                gesture: b
            }
                , d = y(b, c);
            d && (t(c),
                m(b, c),
                a.gestures.session.prevTouch = c,
            b.type !== a.EVENT_END || a.isTouchable || (a.gestures.touch = a.gestures.session = {}))
        };
        b.addEventListener(a.EVENT_START, z),
            b.addEventListener(a.EVENT_MOVE, z),
            b.addEventListener(a.EVENT_END, z),
            b.addEventListener(a.EVENT_CANCEL, z),
            b.addEventListener(a.EVENT_CLICK, function(b) {
                (a.os.android || a.os.ios) && (a.targets.popover && b.target === a.targets.popover || a.targets.tab || a.targets.offcanvas || a.targets.modal) && b.preventDefault()
            }, !0),
            a.isScrolling = !1;
        var A = null;
        b.addEventListener("scroll", function() {
            a.isScrolling = !0,
            A && clearTimeout(A),
                A = setTimeout(function() {
                    a.isScrolling = !1
                }, 250)
        })
    }(mui, window),
    function(a, b) {
        var c = 0
            , d = function(d, e) {
            var f = a.gestures.session
                , g = this.options
                , h = a.now();
            switch (d.type) {
                case a.EVENT_MOVE:
                    h - c > 300 && (c = h,
                        f.flickStart = e.center);
                    break;
                case a.EVENT_END:
                case a.EVENT_CANCEL:
                    e.flick = !1,
                    f.flickStart && g.flickMaxTime > h - c && e.distance > g.flickMinDistince && (e.flick = !0,
                        e.flickTime = h - c,
                        e.flickDistanceX = e.center.x - f.flickStart.x,
                        e.flickDistanceY = e.center.y - f.flickStart.y,
                        a.trigger(f.target, b, e),
                        a.trigger(f.target, b + e.direction, e))
            }
        };
        a.addGesture({
            name: b,
            index: 5,
            handle: d,
            options: {
                flickMaxTime: 200,
                flickMinDistince: 10
            }
        })
    }(mui, "flick"),
    function(a, b) {
        var c = function(c, d) {
            var e = a.gestures.session;
            if (c.type === a.EVENT_END || c.type === a.EVENT_CANCEL) {
                var f = this.options;
                d.swipe = !1,
                d.direction && f.swipeMaxTime > d.deltaTime && d.distance > f.swipeMinDistince && (d.swipe = !0,
                    a.trigger(e.target, b, d),
                    a.trigger(e.target, b + d.direction, d))
            }
        };
        a.addGesture({
            name: b,
            index: 10,
            handle: c,
            options: {
                swipeMaxTime: 300,
                swipeMinDistince: 18
            }
        })
    }(mui, "swipe"),
    function(a, b) {
        var c = function(c, d) {
            var e = a.gestures.session;
            switch (c.type) {
                case a.EVENT_START:
                    break;
                case a.EVENT_MOVE:
                    if (!d.direction || !e.target)
                        return;
                    e.lockDirection && e.startDirection && e.startDirection && e.startDirection !== d.direction && ("up" === e.startDirection || "down" === e.startDirection ? d.direction = d.deltaY < 0 ? "up" : "down" : d.direction = d.deltaX < 0 ? "left" : "right"),
                    e.drag || (e.drag = !0,
                        a.trigger(e.target, b + "start", d)),
                        a.trigger(e.target, b, d),
                        a.trigger(e.target, b + d.direction, d);
                    break;
                case a.EVENT_END:
                case a.EVENT_CANCEL:
                    e.drag && d.isFinal && a.trigger(e.target, b + "end", d)
            }
        };
        a.addGesture({
            name: b,
            index: 20,
            handle: c,
            options: {
                fingers: 1
            }
        })
    }(mui, "drag"),
    function(a, b) {
        var c, d, e = function(e, f) {
            var g = a.gestures.session
                , h = this.options;
            switch (e.type) {
                case a.EVENT_END:
                    if (!f.isFinal)
                        return;
                    var i = g.target;
                    if (!i || i.disabled || i.classList && i.classList.contains("mui-disabled"))
                        return;
                    if (f.distance < h.tapMaxDistance && f.deltaTime < h.tapMaxTime) {
                        if (a.options.gestureConfig.doubletap && c && c === i && d && f.timestamp - d < h.tapMaxInterval)
                            return a.trigger(i, "doubletap", f),
                                d = a.now(),
                                void (c = i);
                        a.trigger(i, b, f),
                            d = a.now(),
                            c = i
                    }
            }
        };
        a.addGesture({
            name: b,
            index: 30,
            handle: e,
            options: {
                fingers: 1,
                tapMaxInterval: 300,
                tapMaxDistance: 5,
                tapMaxTime: 250
            }
        })
    }(mui, "tap"),
    function(a, b) {
        var c, d = function(d, e) {
            var f = a.gestures.session
                , g = this.options;
            switch (d.type) {
                case a.EVENT_START:
                    clearTimeout(c),
                        c = setTimeout(function() {
                            a.trigger(f.target, b, e)
                        }, g.holdTimeout);
                    break;
                case a.EVENT_MOVE:
                    e.distance > g.holdThreshold && clearTimeout(c);
                    break;
                case a.EVENT_END:
                case a.EVENT_CANCEL:
                    clearTimeout(c)
            }
        };
        a.addGesture({
            name: b,
            index: 10,
            handle: d,
            options: {
                fingers: 1,
                holdTimeout: 500,
                holdThreshold: 2
            }
        })
    }(mui, "longtap"),
    function(a, b) {
        var c, d = function(d, e) {
            var f = a.gestures.session
                , g = this.options;
            switch (d.type) {
                case a.EVENT_START:
                    a.options.gestureConfig.hold && (c && clearTimeout(c),
                        c = setTimeout(function() {
                            e.hold = !0,
                                a.trigger(f.target, b, e)
                        }, g.holdTimeout));
                    break;
                case a.EVENT_MOVE:
                    break;
                case a.EVENT_END:
                case a.EVENT_CANCEL:
                    c && (clearTimeout(c) && (c = null),
                        a.trigger(f.target, "release", e))
            }
        };
        a.addGesture({
            name: b,
            index: 10,
            handle: d,
            options: {
                fingers: 1,
                holdTimeout: 0
            }
        })
    }(mui, "hold"),
    function(a, b) {
        var c = function(c, d) {
            var e = this.options
                , f = a.gestures.session;
            switch (c.type) {
                case a.EVENT_START:
                    break;
                case a.EVENT_MOVE:
                    if (a.options.gestureConfig.pinch) {
                        if (d.touches.length < 2)
                            return;
                        f.pinch || (f.pinch = !0,
                            a.trigger(f.target, b + "start", d)),
                            a.trigger(f.target, b, d);
                        var g = d.scale
                            , h = d.rotation
                            , i = "undefined" == typeof d.lastScale ? 1 : d.lastScale
                            , j = 1e-12;
                        g > i ? (i = g - j,
                            a.trigger(f.target, b + "out", d)) : i > g && (i = g + j,
                                a.trigger(f.target, b + "in", d)),
                        Math.abs(h) > e.minRotationAngle && a.trigger(f.target, "rotate", d)
                    }
                    break;
                case a.EVENT_END:
                case a.EVENT_CANCEL:
                    a.options.gestureConfig.pinch && f.pinch && 2 === d.touches.length && (f.pinch = !1,
                        a.trigger(f.target, b + "end", d))
            }
        };
        a.addGesture({
            name: b,
            index: 10,
            handle: c,
            options: {
                minRotationAngle: 0
            }
        })
    }(mui, "pinch"),
    function(a) {
        function b(a, b) {
            var c = "MUI_SCROLL_POSITION_" + document.location.href + "_" + b.src
                , d = parseFloat(localStorage.getItem(c)) || 0;
            d && !function(a) {
                b.onload = function() {
                    window.scrollTo(0, a)
                }
            }(d),
                setInterval(function() {
                    var a = window.scrollY;
                    d !== a && (localStorage.setItem(c, a + ""),
                        d = a)
                }, 100)
        }
        a.global = a.options = {
            gestureConfig: {
                tap: !0,
                doubletap: !1,
                longtap: !1,
                hold: !1,
                flick: !0,
                swipe: !0,
                drag: !0,
                pinch: !1
            }
        },
            a.initGlobal = function(b) {
                return a.options = a.extend(!0, a.global, b),
                    this
            }
        ;
        var c = {};
        a.init = function(b) {
            return a.options = a.extend(!0, a.global, b || {}),
                a.ready(function() {
                    a.doAction("inits", function(b, d) {
                        var e = !(c[d.name] && !d.repeat);
                        e && (d.handle.call(a),
                            c[d.name] = !0)
                    })
                }),
                this
        }
            ,
            a.addInit = function(b) {
                return a.addAction("inits", b)
            }
            ,
            a.addInit({
                name: "iframe",
                index: 100,
                handle: function() {
                    var b = a.options
                        , c = b.subpages || [];
                    !a.os.plus && c.length && d(c[0])
                }
            });
        var d = function(c) {
            var d = document.createElement("div");
            d.className = "mui-iframe-wrapper";
            var e = c.styles || {};
            "string" != typeof e.top && (e.top = "0px"),
            "string" != typeof e.bottom && (e.bottom = "0px"),
                d.style.top = e.top,
                d.style.bottom = e.bottom;
            var f = document.createElement("iframe");
            f.src = c.url,
                f.id = c.id || c.url,
                f.name = f.id,
                d.appendChild(f),
                document.body.appendChild(d),
            a.os.wechat && b(d, f)
        };
        a(function() {
            var b = document.body.classList
                , c = [];
            a.os.ios ? (c.push({
                os: "ios",
                version: a.os.version
            }),
                b.add("mui-ios")) : a.os.android && (c.push({
                    os: "android",
                    version: a.os.version
                }),
                    b.add("mui-android")),
            a.os.wechat && (c.push({
                os: "wechat",
                version: a.os.wechat.version
            }),
                b.add("mui-wechat")),
            c.length && a.each(c, function(c, d) {
                var e = "";
                d.version && a.each(d.version.split("."), function(c, f) {
                    e = e + (e ? "-" : "") + f,
                        b.add(a.className(d.os + "-" + e))
                })
            })
        })
    }(mui),
    function(a) {
        var b = {
            swipeBack: !1,
            preloadPages: [],
            preloadLimit: 10,
            keyEventBind: {
                backbutton: !0,
                menubutton: !0
            },
            titleConfig: {
                height: "44px",
                backgroundColor: "#f7f7f7",
                bottomBorderColor: "#cccccc",
                title: {
                    text: "",
                    position: {
                        top: 0,
                        left: 0,
                        width: "100%",
                        height: "100%"
                    },
                    styles: {
                        color: "#000000",
                        align: "center",
                        family: "'Helvetica Neue',Helvetica,sans-serif",
                        size: "17px",
                        style: "normal",
                        weight: "normal",
                        fontSrc: ""
                    }
                },
                back: {
                    image: {
                        base64Data: "",
                        imgSrc: "",
                        sprite: {
                            top: "0px",
                            left: "0px",
                            width: "100%",
                            height: "100%"
                        },
                        position: {
                            top: "10px",
                            left: "10px",
                            width: "24px",
                            height: "24px"
                        }
                    }
                }
            }
        }
            , c = {
            event: "titleUpdate",
            autoShow: !0,
            duration: 300,
            aniShow: "slide-in-right",
            extras: {}
        };
        a.options.show && (c = a.extend(!0, c, a.options.show)),
            a.currentWebview = null,
            a.extend(!0, a.global, b),
            a.extend(!0, a.options, b),
            a.waitingOptions = function(b) {
                return a.extend(!0, {}, {
                    autoShow: !0,
                    title: "",
                    modal: !1
                }, b)
            }
            ,
            a.showOptions = function(b) {
                return a.extend(!0, {}, c, b)
            }
            ,
            a.windowOptions = function(b) {
                return a.extend({
                    scalable: !1,
                    bounce: ""
                }, b)
            }
            ,
            a.plusReady = function(a) {
                return window.plus ? setTimeout(function() {
                    a()
                }, 0) : document.addEventListener("plusready", function() {
                    a()
                }, !1),
                    this
            }
            ,
            a.fire = function(b, c, d) {
                if (b) {
                    if ("undefined" == typeof d)
                        d = "";
                    else {
                        if ("boolean" == typeof d || "number" == typeof d)
                            return void b.evalJS("typeof mui!=='undefined'&&mui.receive('" + c + "'," + d + ")");
                        a.isPlainObject(d) && (d = JSON.stringify(d || {}).replace(/\'/g, "\\u0027").replace(/\\/g, "\\u005c"))
                    }
                    b.evalJS("typeof mui!=='undefined'&&mui.receive('" + c + "','" + d + "')")
                }
            }
            ,
            a.receive = function(b, c) {
                if (b) {
                    try {
                        c && "string" == typeof c && (c = JSON.parse(c))
                    } catch (d) {}
                    a.trigger(document, b, c)
                }
            }
        ;
        var d = function(b) {
            if (!b.preloaded) {
                a.fire(b, "preload");
                for (var c = b.children(), d = 0; d < c.length; d++)
                    a.fire(c[d], "preload");
                b.preloaded = !0
            }
        }
            , e = function(b, c, d) {
            if (d) {
                if (!b[c + "ed"]) {
                    a.fire(b, c);
                    for (var e = b.children(), f = 0; f < e.length; f++)
                        a.fire(e[f], c);
                    b[c + "ed"] = !0
                }
            } else {
                a.fire(b, c);
                for (var e = b.children(), f = 0; f < e.length; f++)
                    a.fire(e[f], c)
            }
        };
        a.openWindow = function(b, f, g) {
            if ("object" == typeof b ? (g = b,
                    b = g.url,
                    f = g.id || b) : "object" == typeof f ? (g = f,
                    f = g.id || b) : f = f || b,
                    !a.os.plus)
                return void (a.os.ios || a.os.android ? window.top.location.href = b : window.parent.location.href = b);
            if (window.plus) {
                g = g || {};
                var h, i, j = g.params || {}, k = null, l = null;
                if (a.webviews[f] ? (l = a.webviews[f],
                    plus.webview.getWebviewById(f) && (k = l.webview)) : g.createNew !== !0 && (k = plus.webview.getWebviewById(f)),
                        k)
                    return h = l ? l.show : c,
                        h = g.show ? a.extend(h, g.show) : h,
                    h.autoShow && k.show(h.aniShow, h.duration, function() {
                        d(k),
                            e(k, "pagebeforeshow", !1)
                    }),
                    l && l.afterShowMethodName && k.evalJS(l.afterShowMethodName + "('" + JSON.stringify(j) + "')"),
                        k;
                if (!b)
                    throw new Error("webview[" + f + "] does not exist");
                var m = a.waitingOptions(g.waiting);
                if (m.autoShow && (i = plus.nativeUI.showWaiting(m.title, m.options)),
                        g = a.extend(g, {
                            id: f,
                            url: b
                        }),
                        k = a.createWindow(g),
                        h = a.showOptions(g.show),
                        h.autoShow) {
                    var n = function() {
                        i && i.close(),
                            k.show(h.aniShow, h.duration, function() {}, h.extras),
                        g.afterShowMethodName && k.evalJS(g.afterShowMethodName + "('" + JSON.stringify(j) + "')")
                    };
                    k.addEventListener(h.event, n, !1),
                        k.addEventListener("loaded", function() {
                            d(k),
                                e(k, "pagebeforeshow", !1)
                        }, !1)
                }
                return k
            }
        }
            ,
            a.openWindowWithTitle = function(b, f) {
                b = b || {};
                var g = b.url
                    , h = b.id || g;
                if (!a.os.plus)
                    return void (a.os.ios || a.os.android ? window.top.location.href = g : window.parent.location.href = g);
                if (window.plus) {
                    var i, j, k = b.params || {}, l = null, m = null;
                    if (a.webviews[h] ? (m = a.webviews[h],
                        plus.webview.getWebviewById(h) && (l = m.webview)) : b.createNew !== !0 && (l = plus.webview.getWebviewById(h)),
                            l)
                        return i = m ? m.show : c,
                            i = b.show ? a.extend(i, b.show) : i,
                        i.autoShow && l.show(i.aniShow, i.duration, function() {
                            d(l),
                                e(l, "pagebeforeshow", !1)
                        }),
                        m && m.afterShowMethodName && l.evalJS(m.afterShowMethodName + "('" + JSON.stringify(k) + "')"),
                            l;
                    if (!g)
                        throw new Error("webview[" + h + "] does not exist");
                    var n = a.waitingOptions(b.waiting);
                    if (n.autoShow && (j = plus.nativeUI.showWaiting(n.title, n.options)),
                            b = a.extend(b, {
                                id: h,
                                url: g
                            }),
                            l = a.createWindow(b),
                            f) {
                        a.extend(!0, a.options.titleConfig, f);
                        var o = a.options.titleConfig.id ? a.options.titleConfig.id : h + "_title"
                            , p = new plus.nativeObj.View(o,{
                            top: 0,
                            height: a.options.titleConfig.height,
                            width: "100%",
                            dock: "top",
                            position: "dock"
                        });
                        p.drawRect(a.options.titleConfig.backgroundColor);
                        var q = parseInt(a.options.titleConfig.height) - 1;
                        if (p.drawRect(a.options.titleConfig.bottomBorderColor, {
                                top: q + "px",
                                left: "0px"
                            }),
                                a.options.titleConfig.title.text) {
                            var r = a.options.titleConfig.title;
                            p.drawText(r.text, r.position, r.styles)
                        }
                        var s = a.options.titleConfig.back
                            , t = null
                            , u = s.image;
                        if (u.base64Data || u.imgSrc) {
                            t = {
                                left: parseInt(u.position.left),
                                right: parseInt(u.position.left) + parseInt(u.position.width)
                            };
                            var v = new plus.nativeObj.Bitmap(h + "_back");
                            u.base64Data ? v.loadBase64Data(u.base64Data) : v.load(u.imgSrc),
                                p.drawBitmap(v, u.sprite, u.position)
                        }
                        p.setTouchEventRect({
                            top: "0px",
                            left: "0px",
                            width: "100%",
                            height: "100%"
                        }),
                            p.interceptTouchEvent(!0),
                            p.addEventListener("click", function(b) {
                                var c = b.clientX;
                                t && c > t.left && c < t.right && (s.click && a.isFunction(s.click) ? s.click() : l.evalJS("mui&&mui.back();"))
                            }, !1),
                            l.append(p)
                    }
                    return i = a.showOptions(b.show),
                    i.autoShow && l.addEventListener(i.event, function() {
                        j && j.close(),
                            l.show(i.aniShow, i.duration, function() {}, i.extras)
                    }, !1),
                        l
                }
            }
            ,
            a.createWindow = function(b, c) {
                if (window.plus) {
                    var d, e = b.id || b.url;
                    if (b.preload) {
                        a.webviews[e] && a.webviews[e].webview.getURL() ? d = a.webviews[e].webview : (b.createNew !== !0 && (d = plus.webview.getWebviewById(e)),
                        d || (d = plus.webview.create(b.url, e, a.windowOptions(b.styles), a.extend({
                            preload: !0
                        }, b.extras)),
                        b.subpages && a.each(b.subpages, function(b, c) {
                            var e = c.id || c.url;
                            if (e) {
                                var f = plus.webview.getWebviewById(e);
                                f || (f = plus.webview.create(c.url, e, a.windowOptions(c.styles), a.extend({
                                    preload: !0
                                }, c.extras))),
                                    d.append(f)
                            }
                        }))),
                            a.webviews[e] = {
                                webview: d,
                                preload: !0,
                                show: a.showOptions(b.show),
                                afterShowMethodName: b.afterShowMethodName
                            };
                        var f = a.data.preloads
                            , g = f.indexOf(e);
                        if (~g && f.splice(g, 1),
                                f.push(e),
                            f.length > a.options.preloadLimit) {
                            var h = a.data.preloads.shift()
                                , i = a.webviews[h];
                            i && i.webview && a.closeAll(i.webview),
                                delete a.webviews[h]
                        }
                    } else
                        c !== !1 && (d = plus.webview.create(b.url, e, a.windowOptions(b.styles), b.extras),
                        b.subpages && a.each(b.subpages, function(b, c) {
                            var e = c.id || c.url
                                , f = plus.webview.getWebviewById(e);
                            f || (f = plus.webview.create(c.url, e, a.windowOptions(c.styles), c.extras)),
                                d.append(f)
                        }));
                    return d
                }
            }
            ,
            a.preload = function(b) {
                return b.preload || (b.preload = !0),
                    a.createWindow(b)
            }
            ,
            a.closeOpened = function(b) {
                var c = b.opened();
                if (c)
                    for (var d = 0, e = c.length; e > d; d++) {
                        var f = c[d]
                            , g = f.opened();
                        g && g.length > 0 ? (a.closeOpened(f),
                            f.close("none")) : f.parent() !== b && f.close("none")
                    }
            }
            ,
            a.closeAll = function(b, c) {
                a.closeOpened(b),
                    c ? b.close(c) : b.close()
            }
            ,
            a.createWindows = function(b) {
                a.each(b, function(b, c) {
                    a.createWindow(c, !1)
                })
            }
            ,
            a.appendWebview = function(b) {
                if (window.plus) {
                    var c, d = b.id || b.url;
                    return a.webviews[d] || (plus.webview.getWebviewById(d) || (c = plus.webview.create(b.url, d, b.styles, b.extras)),
                        plus.webview.currentWebview().append(c),
                        a.webviews[d] = b),
                        c
                }
            }
            ,
            a.webviews = {},
            a.data.preloads = [],
            a.plusReady(function() {
                a.currentWebview = plus.webview.currentWebview()
            }),
            a.addInit({
                name: "5+",
                index: 100,
                handle: function() {
                    var b = a.options
                        , c = b.subpages || [];
                    a.os.plus && a.plusReady(function() {
                        a.each(c, function(b, c) {
                            a.appendWebview(c)
                        }),
                        plus.webview.currentWebview() === plus.webview.getWebviewById(plus.runtime.appid) && setTimeout(function() {
                            d(plus.webview.currentWebview())
                        }, 300),
                        a.os.ios && a.options.statusBarBackground && plus.navigator.setStatusBarBackground(a.options.statusBarBackground),
                        a.os.android && parseFloat(a.os.version) < 4.4 && null == plus.webview.currentWebview().parent() && document.addEventListener("resume", function() {
                            var a = document.body;
                            a.style.display = "none",
                                setTimeout(function() {
                                    a.style.display = ""
                                }, 10)
                        })
                    })
                }
            }),
            window.addEventListener("preload", function() {
                var b = a.options.preloadPages || [];
                a.plusReady(function() {
                    a.each(b, function(b, c) {
                        a.createWindow(a.extend(c, {
                            preload: !0
                        }))
                    })
                })
            }),
            a.supportStatusbarOffset = function() {
                return a.os.plus && a.os.ios && parseFloat(a.os.version) >= 7
            }
            ,
            a.ready(function() {
                a.supportStatusbarOffset() && document.body.classList.add("mui-statusbar")
            })
    }(mui),
    function(a, b) {
        a.addBack = function(b) {
            return a.addAction("backs", b)
        }
            ,
            a.addBack({
                name: "browser",
                index: 100,
                handle: function() {
                    return b.history.length > 1 ? (b.history.back(),
                        !0) : !1
                }
            }),
            a.back = function() {
                ("function" != typeof a.options.beforeback || a.options.beforeback() !== !1) && a.doAction("backs")
            }
            ,
            b.addEventListener("tap", function(b) {
                var c = a.targets.action;
                c && c.classList.contains("mui-action-back") && (a.back(),
                    a.targets.action = !1)
            }),
            b.addEventListener("swiperight", function(b) {
                var c = b.detail;
                a.options.swipeBack === !0 && Math.abs(c.angle) < 3 && a.back()
            })
    }(mui, window),
    function(a, b) {
        a.os.plus && a.os.android && a.addBack({
            name: "mui",
            index: 5,
            handle: function() {
                if (a.targets._popover && a.targets._popover.classList.contains("mui-active"))
                    return a(a.targets._popover).popover("hide"),
                        !0;
                var b = document.querySelector(".mui-off-canvas-wrap.mui-active");
                if (b)
                    return a(b).offCanvas("close"),
                        !0;
                var c = a.isFunction(a.getPreviewImage) && a.getPreviewImage();
                return c && c.isShown() ? (c.close(),
                    !0) : a.closePopup()
            }
        }),
            a.__back__first = null,
            a.addBack({
                name: "5+",
                index: 10,
                handle: function() {
                    if (!b.plus)
                        return !1;
                    var c = plus.webview.currentWebview()
                        , d = c.parent();
                    return d ? d.evalJS("mui&&mui.back();") : c.canBack(function(d) {
                        d.canBack ? b.history.back() : c.id === plus.runtime.appid ? a.__back__first ? (new Date).getTime() - a.__back__first < 2e3 && plus.runtime.quit() : (a.__back__first = (new Date).getTime(),
                            mui.toast("再按一次退出应用"),
                            setTimeout(function() {
                                a.__back__first = null
                            }, 2e3)) : c.preload ? c.hide("auto") : a.closeAll(c)
                    }),
                        !0
                }
            }),
            a.menu = function() {
                var c = document.querySelector(".mui-action-menu");
                if (c)
                    a.trigger(c, a.EVENT_START),
                        a.trigger(c, "tap");
                else if (b.plus) {
                    var d = a.currentWebview
                        , e = d.parent();
                    e && e.evalJS("mui&&mui.menu();");
                }
            }
        ;
        var c = function() {
            a.back()
        }
            , d = function() {
            a.menu()
        };
        a.plusReady(function() {
            a.options.keyEventBind.backbutton && plus.key.addEventListener("backbutton", c, !1),
            a.options.keyEventBind.menubutton && plus.key.addEventListener("menubutton", d, !1)
        }),
            a.addInit({
                name: "keyEventBind",
                index: 1e3,
                handle: function() {
                    a.plusReady(function() {
                        a.options.keyEventBind.backbutton || plus.key.removeEventListener("backbutton", c),
                        a.options.keyEventBind.menubutton || plus.key.removeEventListener("menubutton", d)
                    })
                }
            })
    }(mui, window),
    function(a) {
        a.addInit({
            name: "pullrefresh",
            index: 1e3,
            handle: function() {
                var b = a.options
                    , c = b.pullRefresh || {}
                    , d = c.down && c.down.hasOwnProperty("callback")
                    , e = c.up && c.up.hasOwnProperty("callback");
                if (d || e) {
                    var f = c.container;
                    if (f) {
                        var g = a(f);
                        1 === g.length && (a.os.plus && a.os.android ? a.plusReady(function() {
                            var b = plus.webview.currentWebview();
                            if (e) {
                                var f = {};
                                f.up = c.up,
                                    f.webviewId = b.id || b.getURL(),
                                    g.pullRefresh(f)
                            }
                            if (d) {
                                var h = b.parent()
                                    , i = b.id || b.getURL();
                                if (h) {
                                    e || g.pullRefresh({
                                        webviewId: i
                                    });
                                    var j = {
                                        webviewId: i
                                    };
                                    j.down = a.extend({}, c.down),
                                        j.down.callback = "_CALLBACK",
                                        h.evalJS("mui&&mui(document.querySelector('.mui-content')).pullRefresh('" + JSON.stringify(j) + "')")
                                }
                            }
                        }) : g.pullRefresh(c))
                    }
                }
            }
        })
    }(mui),
    function(a, b, c) {
        var d = "application/json"
            , e = "text/html"
            , f = /<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi
            , g = /^(?:text|application)\/javascript/i
            , h = /^(?:text|application)\/xml/i
            , i = /^\s*$/;
        a.ajaxSettings = {
            type: "GET",
            beforeSend: a.noop,
            success: a.noop,
            error: a.noop,
            complete: a.noop,
            context: null,
            xhr: function(a) {
                return new b.XMLHttpRequest
            },
            accepts: {
                script: "text/javascript, application/javascript, application/x-javascript",
                json: d,
                xml: "application/xml, text/xml",
                html: e,
                text: "text/plain"
            },
            timeout: 0,
            processData: !0,
            cache: !0
        };
        var j = function(a, b) {
            var c = b.context;
            return b.beforeSend.call(c, a, b) === !1 ? !1 : void 0
        }
            , k = function(a, b, c) {
            c.success.call(c.context, a, "success", b),
                m("success", b, c)
        }
            , l = function(a, b, c, d) {
            d.error.call(d.context, c, b, a),
                m(b, c, d)
        }
            , m = function(a, b, c) {
            c.complete.call(c.context, b, a)
        }
            , n = function(b, c, d, e) {
            var f, g = a.isArray(c), h = a.isPlainObject(c);
            a.each(c, function(c, i) {
                f = a.type(i),
                e && (c = d ? e : e + "[" + (h || "object" === f || "array" === f ? c : "") + "]"),
                    !e && g ? b.add(i.name, i.value) : "array" === f || !d && "object" === f ? n(b, i, d, c) : b.add(c, i)
            })
        }
            , o = function(b) {
            if (b.processData && b.data && "string" != typeof b.data) {
                var e = b.contentType;
                !e && b.headers && (e = b.headers["Content-Type"]),
                    e && ~e.indexOf(d) ? b.data = JSON.stringify(b.data) : b.data = a.param(b.data, b.traditional)
            }
            !b.data || b.type && "GET" !== b.type.toUpperCase() || (b.url = p(b.url, b.data),
                b.data = c)
        }
            , p = function(a, b) {
            return "" === b ? a : (a + "&" + b).replace(/[&?]{1,2}/, "?")
        }
            , q = function(a) {
            return a && (a = a.split(";", 2)[0]),
            a && (a === e ? "html" : a === d ? "json" : g.test(a) ? "script" : h.test(a) && "xml") || "text"
        }
            , r = function(b, d, e, f) {
            return a.isFunction(d) && (f = e,
                e = d,
                d = c),
            a.isFunction(e) || (f = e,
                e = c),
                {
                    url: b,
                    data: d,
                    success: e,
                    dataType: f
                }
        };
        a.ajax = function(d, e) {
            "object" == typeof d && (e = d,
                d = c);
            var f = e || {};
            f.url = d || f.url;
            for (var g in a.ajaxSettings)
                f[g] === c && (f[g] = a.ajaxSettings[g]);
            o(f);
            var h = f.dataType;
            f.cache !== !1 && (e && e.cache === !0 || "script" !== h) || (f.url = p(f.url, "_=" + a.now()));
            var m, n = f.accepts[h && h.toLowerCase()], r = {}, s = function(a, b) {
                r[a.toLowerCase()] = [a, b]
            }, t = /^([\w-]+:)\/\//.test(f.url) ? RegExp.$1 : b.location.protocol, u = f.xhr(f), v = u.setRequestHeader;
            if (s("X-Requested-With", "XMLHttpRequest"),
                    s("Accept", n || "*/*"),
                (n = f.mimeType || n) && (n.indexOf(",") > -1 && (n = n.split(",", 2)[0]),
                u.overrideMimeType && u.overrideMimeType(n)),
                (f.contentType || f.contentType !== !1 && f.data && "GET" !== f.type.toUpperCase()) && s("Content-Type", f.contentType || "application/x-www-form-urlencoded"),
                    f.headers)
                for (var w in f.headers)
                    s(w, f.headers[w]);
            if (u.setRequestHeader = s,
                    u.onreadystatechange = function() {
                        if (4 === u.readyState) {
                            u.onreadystatechange = a.noop,
                                clearTimeout(m);
                            var b, c = !1, d = "file:" === t;
                            if (u.status >= 200 && u.status < 300 || 304 === u.status || 0 === u.status && d && u.responseText) {
                                h = h || q(f.mimeType || u.getResponseHeader("content-type")),
                                    b = u.responseText;
                                try {
                                    "script" === h ? (1,
                                        eval)(b) : "xml" === h ? b = u.responseXML : "json" === h && (b = i.test(b) ? null : a.parseJSON(b))
                                } catch (e) {
                                    c = e
                                }
                                c ? l(c, "parsererror", u, f) : k(b, u, f)
                            } else {
                                var g = u.status ? "error" : "abort"
                                    , j = u.statusText || null;
                                d && (g = "error",
                                    j = "404"),
                                    l(j, g, u, f)
                            }
                        }
                    }
                    ,
                j(u, f) === !1)
                return u.abort(),
                    l(null, "abort", u, f),
                    u;
            if (f.xhrFields)
                for (var w in f.xhrFields)
                    u[w] = f.xhrFields[w];
            var x = "async"in f ? f.async : !0;
            u.open(f.type.toUpperCase(), f.url, x, f.username, f.password);
            for (var w in r)
                r.hasOwnProperty(w) && v.apply(u, r[w]);
            return f.timeout > 0 && (m = setTimeout(function() {
                u.onreadystatechange = a.noop,
                    u.abort(),
                    l(null, "timeout", u, f)
            }, f.timeout)),
                u.send(f.data ? f.data : null),
                u
        }
            ,
            a.param = function(a, b) {
                var c = [];
                return c.add = function(a, b) {
                    this.push(encodeURIComponent(a) + "=" + encodeURIComponent(b))
                }
                    ,
                    n(c, a, b),
                    c.join("&").replace(/%20/g, "+")
            }
            ,
            a.get = function() {
                return a.ajax(r.apply(null, arguments))
            }
            ,
            a.post = function() {
                var b = r.apply(null, arguments);
                return b.type = "POST",
                    a.ajax(b)
            }
            ,
            a.getJSON = function() {
                var b = r.apply(null, arguments);
                return b.dataType = "json",
                    a.ajax(b)
            }
            ,
            a.fn.load = function(b, c, d) {
                if (!this.length)
                    return this;
                var e, g = this, h = b.split(/\s/), i = r(b, c, d), j = i.success;
                return h.length > 1 && (i.url = h[0],
                    e = h[1]),
                    i.success = function(a) {
                        if (e) {
                            var b = document.createElement("div");
                            b.innerHTML = a.replace(f, "");
                            var c = document.createElement("div")
                                , d = b.querySelectorAll(e);
                            if (d && d.length > 0)
                                for (var h = 0, i = d.length; i > h; h++)
                                    c.appendChild(d[h]);
                            g[0].innerHTML = c.innerHTML
                        } else
                            g[0].innerHTML = a;
                        j && j.apply(g, arguments)
                    }
                    ,
                    a.ajax(i),
                    this
            }
    }(mui, window),
    function(a) {
        var b = document.createElement("a");
        b.href = window.location.href,
            a.plusReady(function() {
                a.ajaxSettings = a.extend(a.ajaxSettings, {
                    xhr: function(a) {
                        if (a.crossDomain)
                            return new plus.net.XMLHttpRequest;
                        if ("file:" !== b.protocol) {
                            var c = document.createElement("a");
                            if (c.href = a.url,
                                    c.href = c.href,
                                    a.crossDomain = b.protocol + "//" + b.host != c.protocol + "//" + c.host,
                                    a.crossDomain)
                                return new plus.net.XMLHttpRequest
                        }
                        return new window.XMLHttpRequest
                    }
                })
            })
    }(mui),
    function(a, b, c) {
        a.offset = function(a) {
            var d = {
                top: 0,
                left: 0
            };
            return typeof a.getBoundingClientRect !== c && (d = a.getBoundingClientRect()),
                {
                    top: d.top + b.pageYOffset - a.clientTop,
                    left: d.left + b.pageXOffset - a.clientLeft
                }
        }
    }(mui, window),
    function(a, b) {
        a.scrollTo = function(a, c, d) {
            c = c || 1e3;
            var e = function(c) {
                if (0 >= c)
                    return b.scrollTo(0, a),
                        void (d && d());
                var f = a - b.scrollY;
                setTimeout(function() {
                    b.scrollTo(0, b.scrollY + f / c * 10),
                        e(c - 10)
                }, 16.7)
            };
            e(c)
        }
            ,
            a.animationFrame = function(a) {
                var b, c, d;
                return function() {
                    b = arguments,
                        d = this,
                    c || (c = !0,
                        requestAnimationFrame(function() {
                            a.apply(d, b),
                                c = !1
                        }))
                }
            }
    }(mui, window),
    function(a) {
        var b = !1
            , c = /xyz/.test(function() {
            xyz
        }) ? /\b_super\b/ : /.*/
            , d = function() {};
        d.extend = function(a) {
            function d() {
                !b && this.init && this.init.apply(this, arguments)
            }
            var e = this.prototype;
            b = !0;
            var f = new this;
            b = !1;
            for (var g in a)
                f[g] = "function" == typeof a[g] && "function" == typeof e[g] && c.test(a[g]) ? function(a, b) {
                    return function() {
                        var c = this._super;
                        this._super = e[a];
                        var d = b.apply(this, arguments);
                        return this._super = c,
                            d
                    }
                }(g, a[g]) : a[g];
            return d.prototype = f,
                d.prototype.constructor = d,
                d.extend = arguments.callee,
                d
        }
            ,
            a.Class = d
    }(mui),
    function(a, b, c) {
        var d = "mui-pull-top-pocket"
            , e = "mui-pull-bottom-pocket"
            , f = "mui-pull"
            , g = "mui-pull-loading"
            , h = "mui-pull-caption"
            , i = "mui-pull-caption-down"
            , j = "mui-pull-caption-refresh"
            , k = "mui-pull-caption-nomore"
            , l = "mui-icon"
            , m = "mui-spinner"
            , n = "mui-icon-pulldown"
            , o = "mui-block"
            , p = "mui-hidden"
            , q = "mui-visibility"
            , r = g + " " + l + " " + n
            , s = g + " " + l + " " + n
            , t = g + " " + l + " " + m
            , u = ['<div class="' + f + '">', '<div class="{icon}"></div>', '<div class="' + h + '">{contentrefresh}</div>', "</div>"].join("")
            , v = {
            init: function(b, c) {
                this._super(b, a.extend(!0, {
                    scrollY: !0,
                    scrollX: !1,
                    indicators: !0,
                    deceleration: .003,
                    down: {
                        height: 50,
                        contentinit: "下拉可以刷新",
                        contentdown: "下拉可以刷新",
                        contentover: "释放立即刷新",
                        contentrefresh: "正在刷新..."
                    },
                    up: {
                        height: 50,
                        auto: !1,
                        contentinit: "上拉显示更多",
                        contentdown: "上拉显示更多",
                        contentrefresh: "正在加载...",
                        contentnomore: "没有更多数据了",
                        duration: 300
                    }
                }, c))
            },
            _init: function() {
                this._super(),
                    this._initPocket()
            },
            _initPulldownRefresh: function() {
                this.pulldown = !0,
                    this.pullPocket = this.topPocket,
                    this.pullPocket.classList.add(o),
                    this.pullPocket.classList.add(q),
                    this.pullCaption = this.topCaption,
                    this.pullLoading = this.topLoading
            },
            _initPullupRefresh: function() {
                this.pulldown = !1,
                    this.pullPocket = this.bottomPocket,
                    this.pullPocket.classList.add(o),
                    this.pullPocket.classList.add(q),
                    this.pullCaption = this.bottomCaption,
                    this.pullLoading = this.bottomLoading
            },
            _initPocket: function() {
                var a = this.options;
                a.down && a.down.hasOwnProperty("callback") && (this.topPocket = this.scroller.querySelector("." + d),
                this.topPocket || (this.topPocket = this._createPocket(d, a.down, s),
                    this.wrapper.insertBefore(this.topPocket, this.wrapper.firstChild)),
                    this.topLoading = this.topPocket.querySelector("." + g),
                    this.topCaption = this.topPocket.querySelector("." + h)),
                a.up && a.up.hasOwnProperty("callback") && (this.bottomPocket = this.scroller.querySelector("." + e),
                this.bottomPocket || (this.bottomPocket = this._createPocket(e, a.up, t),
                    this.scroller.appendChild(this.bottomPocket)),
                    this.bottomLoading = this.bottomPocket.querySelector("." + g),
                    this.bottomCaption = this.bottomPocket.querySelector("." + h),
                    this.wrapper.addEventListener("scrollbottom", this))
            },
            _createPocket: function(a, c, d) {
                var e = b.createElement("div");
                return e.className = a,
                    e.innerHTML = u.replace("{contentrefresh}", c.contentinit).replace("{icon}", d),
                    e
            },
            _resetPullDownLoading: function() {
                var a = this.pullLoading;
                a && (this.pullCaption.innerHTML = this.options.down.contentdown,
                    a.style.webkitTransition = "",
                    a.style.webkitTransform = "",
                    a.style.webkitAnimation = "",
                    a.className = s)
            },
            _setCaptionClass: function(a, b, c) {
                if (!a)
                    switch (c) {
                        case this.options.up.contentdown:
                            b.className = h + " " + i;
                            break;
                        case this.options.up.contentrefresh:
                            b.className = h + " " + j;
                            break;
                        case this.options.up.contentnomore:
                            b.className = h + " " + k
                    }
            },
            _setCaption: function(a, b) {
                if (!this.loading) {
                    var c = this.options
                        , d = this.pullPocket
                        , e = this.pullCaption
                        , f = this.pullLoading
                        , g = this.pulldown
                        , h = this;
                    d && (b ? setTimeout(function() {
                        e.innerHTML = h.lastTitle = a,
                            g ? f.className = s : (h._setCaptionClass(!1, e, a),
                                f.className = t),
                            f.style.webkitAnimation = "",
                            f.style.webkitTransition = "",
                            f.style.webkitTransform = ""
                    }, 100) : a !== this.lastTitle && (e.innerHTML = a,
                            g ? a === c.down.contentrefresh ? (f.className = t,
                                f.style.webkitAnimation = "spinner-spin 1s step-end infinite") : a === c.down.contentover ? (f.className = r,
                                f.style.webkitTransition = "-webkit-transform 0.3s ease-in",
                                f.style.webkitTransform = "rotate(180deg)") : a === c.down.contentdown && (f.className = s,
                                    f.style.webkitTransition = "-webkit-transform 0.3s ease-in",
                                    f.style.webkitTransform = "rotate(0deg)") : (a === c.up.contentrefresh ? f.className = t + " " + q : f.className = t + " " + p,
                                h._setCaptionClass(!1, e, a)),
                            this.lastTitle = a))
                }
            }
        };
        a.PullRefresh = v
    }(mui, document),
    function(a, b, c, d) {
        var e = "mui-scroll"
            , f = "mui-scrollbar"
            , g = "mui-scrollbar-indicator"
            , h = f + "-vertical"
            , i = f + "-horizontal"
            , j = "mui-active"
            , k = {
            quadratic: {
                style: "cubic-bezier(0.25, 0.46, 0.45, 0.94)",
                fn: function(a) {
                    return a * (2 - a)
                }
            },
            circular: {
                style: "cubic-bezier(0.1, 0.57, 0.1, 1)",
                fn: function(a) {
                    return Math.sqrt(1 - --a * a)
                }
            },
            outCirc: {
                style: "cubic-bezier(0.075, 0.82, 0.165, 1)"
            },
            outCubic: {
                style: "cubic-bezier(0.165, 0.84, 0.44, 1)"
            }
        }
            , l = a.Class.extend({
            init: function(b, c) {
                this.wrapper = this.element = b,
                    this.scroller = this.wrapper.children[0],
                    this.scrollerStyle = this.scroller && this.scroller.style,
                    this.stopped = !1,
                    this.options = a.extend(!0, {
                        scrollY: !0,
                        scrollX: !1,
                        startX: 0,
                        startY: 0,
                        indicators: !0,
                        stopPropagation: !1,
                        hardwareAccelerated: !0,
                        fixedBadAndorid: !1,
                        preventDefaultException: {
                            tagName: /^(INPUT|TEXTAREA|BUTTON|SELECT|VIDEO)$/
                        },
                        momentum: !0,
                        snapX: .5,
                        snap: !1,
                        bounce: !0,
                        bounceTime: 500,
                        bounceEasing: k.outCirc,
                        scrollTime: 500,
                        scrollEasing: k.outCubic,
                        directionLockThreshold: 5,
                        parallaxElement: !1,
                        parallaxRatio: .5
                    }, c),
                    this.x = 0,
                    this.y = 0,
                    this.translateZ = this.options.hardwareAccelerated ? " translateZ(0)" : "",
                    this._init(),
                this.scroller && (this.refresh(),
                    this.scrollTo(this.options.startX, this.options.startY))
            },
            _init: function() {
                this._initParallax(),
                    this._initIndicators(),
                    this._initEvent()
            },
            _initParallax: function() {
                this.options.parallaxElement && (this.parallaxElement = c.querySelector(this.options.parallaxElement),
                    this.parallaxStyle = this.parallaxElement.style,
                    this.parallaxHeight = this.parallaxElement.offsetHeight,
                    this.parallaxImgStyle = this.parallaxElement.querySelector("img").style)
            },
            _initIndicators: function() {
                var a = this;
                if (a.indicators = [],
                        this.options.indicators) {
                    var b, c = [];
                    a.options.scrollY && (b = {
                        el: this._createScrollBar(h),
                        listenX: !1
                    },
                        this.wrapper.appendChild(b.el),
                        c.push(b)),
                    this.options.scrollX && (b = {
                        el: this._createScrollBar(i),
                        listenY: !1
                    },
                        this.wrapper.appendChild(b.el),
                        c.push(b));
                    for (var d = c.length; d--; )
                        this.indicators.push(new m(this,c[d]))
                }
            },
            _initSnap: function() {
                this.currentPage = {},
                    this.pages = [];
                for (var a = this.snaps, b = a.length, c = 0, d = -1, e = 0, f = 0, g = 0, h = 0, i = 0; b > i; i++) {
                    var k = a[i]
                        , l = k.offsetLeft
                        , m = k.offsetWidth;
                    (0 === i || l <= a[i - 1].offsetLeft) && (c = 0,
                        d++),
                    this.pages[c] || (this.pages[c] = []),
                        e = this._getSnapX(l),
                        h = Math.round(m * this.options.snapX),
                        f = e - h,
                        g = e - m + h,
                        this.pages[c][d] = {
                            x: e,
                            leftX: f,
                            rightX: g,
                            pageX: c,
                            element: k
                        },
                    k.classList.contains(j) && (this.currentPage = this.pages[c][0]),
                    e >= this.maxScrollX && c++
                }
                this.options.startX = this.currentPage.x || 0
            },
            _getSnapX: function(a) {
                return Math.max(Math.min(0, -a + this.wrapperWidth / 2), this.maxScrollX)
            },
            _gotoPage: function(a) {
                this.currentPage = this.pages[Math.min(a, this.pages.length - 1)][0];
                for (var b = 0, c = this.snaps.length; c > b; b++)
                    b === a ? this.snaps[b].classList.add(j) : this.snaps[b].classList.remove(j);
                this.scrollTo(this.currentPage.x, 0, this.options.scrollTime)
            },
            _nearestSnap: function(a) {
                if (!this.pages.length)
                    return {
                        x: 0,
                        pageX: 0
                    };
                var b = 0
                    , c = this.pages.length;
                for (a > 0 ? a = 0 : a < this.maxScrollX && (a = this.maxScrollX); c > b; b++) {
                    var d = "left" === this.direction ? this.pages[b][0].leftX : this.pages[b][0].rightX;
                    if (a >= d)
                        return this.pages[b][0]
                }
                return {
                    x: 0,
                    pageX: 0
                }
            },
            _initEvent: function(c) {
                var d = c ? "removeEventListener" : "addEventListener";
                b[d]("orientationchange", this),
                    b[d]("resize", this),
                    this.scroller[d]("webkitTransitionEnd", this),
                    this.wrapper[d](a.EVENT_START, this),
                    this.wrapper[d](a.EVENT_CANCEL, this),
                    this.wrapper[d](a.EVENT_END, this),
                    this.wrapper[d]("drag", this),
                    this.wrapper[d]("dragend", this),
                    this.wrapper[d]("flick", this),
                    this.wrapper[d]("scrollend", this),
                this.options.scrollX && this.wrapper[d]("swiperight", this);
                var e = this.wrapper.querySelector(".mui-segmented-control");
                e && mui(e)[c ? "off" : "on"]("click", "a", a.preventDefault),
                    this.wrapper[d]("scrollstart", this),
                    this.wrapper[d]("refresh", this)
            },
            _handleIndicatorScrollend: function() {
                this.indicators.map(function(a) {
                    a.fade()
                })
            },
            _handleIndicatorScrollstart: function() {
                this.indicators.map(function(a) {
                    a.fade(1)
                })
            },
            _handleIndicatorRefresh: function() {
                this.indicators.map(function(a) {
                    a.refresh()
                })
            },
            handleEvent: function(b) {
                if (this.stopped)
                    return void this.resetPosition();
                switch (b.type) {
                    case a.EVENT_START:
                        this._start(b);
                        break;
                    case "drag":
                        this.options.stopPropagation && b.stopPropagation(),
                            this._drag(b);
                        break;
                    case "dragend":
                    case "flick":
                        this.options.stopPropagation && b.stopPropagation(),
                            this._flick(b);
                        break;
                    case a.EVENT_CANCEL:
                    case a.EVENT_END:
                        this._end(b);
                        break;
                    case "webkitTransitionEnd":
                        this.transitionTimer && this.transitionTimer.cancel(),
                            this._transitionEnd(b);
                        break;
                    case "scrollstart":
                        this._handleIndicatorScrollstart(b);
                        break;
                    case "scrollend":
                        this._handleIndicatorScrollend(b),
                            this._scrollend(b),
                            b.stopPropagation();
                        break;
                    case "orientationchange":
                    case "resize":
                        this._resize();
                        break;
                    case "swiperight":
                        b.stopPropagation();
                        break;
                    case "refresh":
                        this._handleIndicatorRefresh(b)
                }
            },
            _start: function(b) {
                if (this.moved = this.needReset = !1,
                        this._transitionTime(),
                        this.isInTransition) {
                    this.needReset = !0,
                        this.isInTransition = !1;
                    var c = a.parseTranslateMatrix(a.getStyles(this.scroller, "webkitTransform"));
                    this.setTranslate(Math.round(c.x), Math.round(c.y)),
                        a.trigger(this.scroller, "scrollend", this),
                        b.preventDefault()
                }
                this.reLayout(),
                    a.trigger(this.scroller, "beforescrollstart", this)
            },
            _getDirectionByAngle: function(a) {
                return -80 > a && a > -100 ? "up" : a >= 80 && 100 > a ? "down" : a >= 170 || -170 >= a ? "left" : a >= -35 && 10 >= a ? "right" : null
            },
            _drag: function(c) {
                var d = c.detail;
                if ((this.options.scrollY || "up" === d.direction || "down" === d.direction) && a.os.ios && parseFloat(a.os.version) >= 8) {
                    var e = d.gesture.touches[0].clientY;
                    if (e + 10 > b.innerHeight || 10 > e)
                        return void this.resetPosition(this.options.bounceTime)
                }
                var f = isReturn = !1;
                this._getDirectionByAngle(d.angle);
                if ("left" === d.direction || "right" === d.direction ? this.options.scrollX ? (f = !0,
                    this.moved || (a.gestures.session.lockDirection = !0,
                        a.gestures.session.startDirection = d.direction)) : this.options.scrollY && !this.moved && (isReturn = !0) : "up" === d.direction || "down" === d.direction ? this.options.scrollY ? (f = !0,
                    this.moved || (a.gestures.session.lockDirection = !0,
                        a.gestures.session.startDirection = d.direction)) : this.options.scrollX && !this.moved && (isReturn = !0) : isReturn = !0,
                    (this.moved || f) && (c.stopPropagation(),
                    d.gesture && d.gesture.preventDefault()),
                        !isReturn) {
                    this.moved ? c.stopPropagation() : a.trigger(this.scroller, "scrollstart", this);
                    var g = 0
                        , h = 0;
                    this.moved ? (g = d.deltaX - a.gestures.session.prevTouch.deltaX,
                        h = d.deltaY - a.gestures.session.prevTouch.deltaY) : (g = d.deltaX,
                        h = d.deltaY);
                    var i = Math.abs(d.deltaX)
                        , j = Math.abs(d.deltaY);
                    i > j + this.options.directionLockThreshold ? h = 0 : j >= i + this.options.directionLockThreshold && (g = 0),
                        g = this.hasHorizontalScroll ? g : 0,
                        h = this.hasVerticalScroll ? h : 0;
                    var k = this.x + g
                        , l = this.y + h;
                    (k > 0 || k < this.maxScrollX) && (k = this.options.bounce ? this.x + g / 3 : k > 0 ? 0 : this.maxScrollX),
                    (l > 0 || l < this.maxScrollY) && (l = this.options.bounce ? this.y + h / 3 : l > 0 ? 0 : this.maxScrollY),
                    this.requestAnimationFrame || this._updateTranslate(),
                        this.direction = d.deltaX > 0 ? "right" : "left",
                        this.moved = !0,
                        this.x = k,
                        this.y = l,
                        a.trigger(this.scroller, "scroll", this)
                }
            },
            _flick: function(b) {
                if (this.moved) {
                    b.stopPropagation();
                    var c = b.detail;
                    if (this._clearRequestAnimationFrame(),
                        "dragend" !== b.type || !c.flick) {
                        var d = Math.round(this.x)
                            , e = Math.round(this.y);
                        if (this.isInTransition = !1,
                                !this.resetPosition(this.options.bounceTime)) {
                            if (this.scrollTo(d, e),
                                "dragend" === b.type)
                                return void a.trigger(this.scroller, "scrollend", this);
                            var f = 0
                                , g = "";
                            return this.options.momentum && c.flickTime < 300 && (momentumX = this.hasHorizontalScroll ? this._momentum(this.x, c.flickDistanceX, c.flickTime, this.maxScrollX, this.options.bounce ? this.wrapperWidth : 0, this.options.deceleration) : {
                                destination: d,
                                duration: 0
                            },
                                momentumY = this.hasVerticalScroll ? this._momentum(this.y, c.flickDistanceY, c.flickTime, this.maxScrollY, this.options.bounce ? this.wrapperHeight : 0, this.options.deceleration) : {
                                    destination: e,
                                    duration: 0
                                },
                                d = momentumX.destination,
                                e = momentumY.destination,
                                f = Math.max(momentumX.duration, momentumY.duration),
                                this.isInTransition = !0),
                                d != this.x || e != this.y ? ((d > 0 || d < this.maxScrollX || e > 0 || e < this.maxScrollY) && (g = k.quadratic),
                                    void this.scrollTo(d, e, f, g)) : void a.trigger(this.scroller, "scrollend", this)
                        }
                    }
                }
            },
            _end: function(b) {
                this.needReset = !1,
                (!this.moved && this.needReset || b.type === a.EVENT_CANCEL) && this.resetPosition()
            },
            _transitionEnd: function(b) {
                b.target == this.scroller && this.isInTransition && (this._transitionTime(),
                this.resetPosition(this.options.bounceTime) || (this.isInTransition = !1,
                    a.trigger(this.scroller, "scrollend", this)))
            },
            _scrollend: function(b) {
                (0 === this.y && 0 === this.maxScrollY || Math.abs(this.y) > 0 && this.y <= this.maxScrollY) && a.trigger(this.scroller, "scrollbottom", this)
            },
            _resize: function() {
                var a = this;
                clearTimeout(a.resizeTimeout),
                    a.resizeTimeout = setTimeout(function() {
                        a.refresh()
                    }, a.options.resizePolling)
            },
            _transitionTime: function(b) {
                if (b = b || 0,
                        this.scrollerStyle.webkitTransitionDuration = b + "ms",
                    this.parallaxElement && this.options.scrollY && (this.parallaxStyle.webkitTransitionDuration = b + "ms"),
                    this.options.fixedBadAndorid && !b && a.os.isBadAndroid && (this.scrollerStyle.webkitTransitionDuration = "0.001s",
                    this.parallaxElement && this.options.scrollY && (this.parallaxStyle.webkitTransitionDuration = "0.001s")),
                        this.indicators)
                    for (var c = this.indicators.length; c--; )
                        this.indicators[c].transitionTime(b);
                b && (this.transitionTimer && this.transitionTimer.cancel(),
                    this.transitionTimer = a.later(function() {
                        a.trigger(this.scroller, "webkitTransitionEnd")
                    }, b + 100, this))
            },
            _transitionTimingFunction: function(a) {
                if (this.scrollerStyle.webkitTransitionTimingFunction = a,
                    this.parallaxElement && this.options.scrollY && (this.parallaxStyle.webkitTransitionDuration = a),
                        this.indicators)
                    for (var b = this.indicators.length; b--; )
                        this.indicators[b].transitionTimingFunction(a)
            },
            _translate: function(a, b) {
                this.x = a,
                    this.y = b
            },
            _clearRequestAnimationFrame: function() {
                this.requestAnimationFrame && (cancelAnimationFrame(this.requestAnimationFrame),
                    this.requestAnimationFrame = null)
            },
            _updateTranslate: function() {
                var a = this;
                (a.x !== a.lastX || a.y !== a.lastY) && a.setTranslate(a.x, a.y),
                    a.requestAnimationFrame = requestAnimationFrame(function() {
                        a._updateTranslate()
                    })
            },
            _createScrollBar: function(a) {
                var b = c.createElement("div")
                    , d = c.createElement("div");
                return b.className = f + " " + a,
                    d.className = g,
                    b.appendChild(d),
                    a === h ? (this.scrollbarY = b,
                        this.scrollbarIndicatorY = d) : a === i && (this.scrollbarX = b,
                            this.scrollbarIndicatorX = d),
                    this.wrapper.appendChild(b),
                    b
            },
            _preventDefaultException: function(a, b) {
                for (var c in b)
                    if (b[c].test(a[c]))
                        return !0;
                return !1
            },
            _reLayout: function() {
                if (this.hasHorizontalScroll || (this.maxScrollX = 0,
                        this.scrollerWidth = this.wrapperWidth),
                    this.hasVerticalScroll || (this.maxScrollY = 0,
                        this.scrollerHeight = this.wrapperHeight),
                        this.indicators.map(function(a) {
                            a.refresh()
                        }),
                    this.options.snap && "string" == typeof this.options.snap) {
                    var a = this.scroller.querySelectorAll(this.options.snap);
                    this.itemLength = 0,
                        this.snaps = [];
                    for (var b = 0, c = a.length; c > b; b++) {
                        var d = a[b];
                        d.parentNode === this.scroller && (this.itemLength++,
                            this.snaps.push(d))
                    }
                    this._initSnap()
                }
            },
            _momentum: function(a, b, c, e, f, g) {
                var h, i, j = parseFloat(Math.abs(b) / c);
                return g = g === d ? 6e-4 : g,
                    h = a + j * j / (2 * g) * (0 > b ? -1 : 1),
                    i = j / g,
                    e > h ? (h = f ? e - f / 2.5 * (j / 8) : e,
                        b = Math.abs(h - a),
                        i = b / j) : h > 0 && (h = f ? f / 2.5 * (j / 8) : 0,
                            b = Math.abs(a) + h,
                            i = b / j),
                    {
                        destination: Math.round(h),
                        duration: i
                    }
            },
            _getTranslateStr: function(a, b) {
                return this.options.hardwareAccelerated ? "translate3d(" + a + "px," + b + "px,0px) " + this.translateZ : "translate(" + a + "px," + b + "px) "
            },
            setStopped: function(a) {
                this.stopped = !!a
            },
            setTranslate: function(b, c) {
                if (this.x = b,
                        this.y = c,
                        this.scrollerStyle.webkitTransform = this._getTranslateStr(b, c),
                    this.parallaxElement && this.options.scrollY) {
                    var d = c * this.options.parallaxRatio
                        , e = 1 + d / ((this.parallaxHeight - d) / 2);
                    e > 1 ? (this.parallaxImgStyle.opacity = 1 - d / 100 * this.options.parallaxRatio,
                        this.parallaxStyle.webkitTransform = this._getTranslateStr(0, -d) + " scale(" + e + "," + e + ")") : (this.parallaxImgStyle.opacity = 1,
                        this.parallaxStyle.webkitTransform = this._getTranslateStr(0, -1) + " scale(1,1)")
                }
                if (this.indicators)
                    for (var f = this.indicators.length; f--; )
                        this.indicators[f].updatePosition();
                this.lastX = this.x,
                    this.lastY = this.y,
                    a.trigger(this.scroller, "scroll", this)
            },
            reLayout: function() {
                this.wrapper.offsetHeight;
                var b = parseFloat(a.getStyles(this.wrapper, "padding-left")) || 0
                    , c = parseFloat(a.getStyles(this.wrapper, "padding-right")) || 0
                    , d = parseFloat(a.getStyles(this.wrapper, "padding-top")) || 0
                    , e = parseFloat(a.getStyles(this.wrapper, "padding-bottom")) || 0
                    , f = this.wrapper.clientWidth
                    , g = this.wrapper.clientHeight;
                this.scrollerWidth = this.scroller.offsetWidth,
                    this.scrollerHeight = this.scroller.offsetHeight,
                    this.wrapperWidth = f - b - c,
                    this.wrapperHeight = g - d - e,
                    this.maxScrollX = Math.min(this.wrapperWidth - this.scrollerWidth, 0),
                    this.maxScrollY = Math.min(this.wrapperHeight - this.scrollerHeight, 0),
                    this.hasHorizontalScroll = this.options.scrollX && this.maxScrollX < 0,
                    this.hasVerticalScroll = this.options.scrollY && this.maxScrollY < 0,
                    this._reLayout()
            },
            resetPosition: function(a) {
                var b = this.x
                    , c = this.y;
                return a = a || 0,
                    !this.hasHorizontalScroll || this.x > 0 ? b = 0 : this.x < this.maxScrollX && (b = this.maxScrollX),
                    !this.hasVerticalScroll || this.y > 0 ? c = 0 : this.y < this.maxScrollY && (c = this.maxScrollY),
                    b == this.x && c == this.y ? !1 : (this.scrollTo(b, c, a, this.options.scrollEasing),
                        !0)
            },
            _reInit: function() {
                for (var a = this.wrapper.querySelectorAll("." + e), b = 0, c = a.length; c > b; b++)
                    if (a[b].parentNode === this.wrapper) {
                        this.scroller = a[b];
                        break
                    }
                this.scrollerStyle = this.scroller && this.scroller.style
            },
            refresh: function() {
                this._reInit(),
                    this.reLayout(),
                    a.trigger(this.scroller, "refresh", this),
                    this.resetPosition()
            },
            scrollTo: function(a, b, c, d) {
                var d = d || k.circular;
                this.isInTransition = c > 0,
                    this.isInTransition ? (this._clearRequestAnimationFrame(),
                        this._transitionTimingFunction(d.style),
                        this._transitionTime(c),
                        this.setTranslate(a, b)) : this.setTranslate(a, b)
            },
            scrollToBottom: function(a, b) {
                a = a || this.options.scrollTime,
                    this.scrollTo(0, this.maxScrollY, a, b)
            },
            gotoPage: function(a) {
                this._gotoPage(a)
            },
            destroy: function() {
                this._initEvent(!0),
                    delete a.data[this.wrapper.getAttribute("data-scroll")],
                    this.wrapper.setAttribute("data-scroll", "")
            }
        })
            , m = function(b, d) {
            this.wrapper = "string" == typeof d.el ? c.querySelector(d.el) : d.el,
                this.wrapperStyle = this.wrapper.style,
                this.indicator = this.wrapper.children[0],
                this.indicatorStyle = this.indicator.style,
                this.scroller = b,
                this.options = a.extend({
                    listenX: !0,
                    listenY: !0,
                    fade: !1,
                    speedRatioX: 0,
                    speedRatioY: 0
                }, d),
                this.sizeRatioX = 1,
                this.sizeRatioY = 1,
                this.maxPosX = 0,
                this.maxPosY = 0,
            this.options.fade && (this.wrapperStyle.webkitTransform = this.scroller.translateZ,
                this.wrapperStyle.webkitTransitionDuration = this.options.fixedBadAndorid && a.os.isBadAndroid ? "0.001s" : "0ms",
                this.wrapperStyle.opacity = "0")
        };
        m.prototype = {
            handleEvent: function(a) {},
            transitionTime: function(b) {
                b = b || 0,
                    this.indicatorStyle.webkitTransitionDuration = b + "ms",
                this.scroller.options.fixedBadAndorid && !b && a.os.isBadAndroid && (this.indicatorStyle.webkitTransitionDuration = "0.001s")
            },
            transitionTimingFunction: function(a) {
                this.indicatorStyle.webkitTransitionTimingFunction = a
            },
            refresh: function() {
                this.transitionTime(),
                    this.options.listenX && !this.options.listenY ? this.indicatorStyle.display = this.scroller.hasHorizontalScroll ? "block" : "none" : this.options.listenY && !this.options.listenX ? this.indicatorStyle.display = this.scroller.hasVerticalScroll ? "block" : "none" : this.indicatorStyle.display = this.scroller.hasHorizontalScroll || this.scroller.hasVerticalScroll ? "block" : "none",
                    this.wrapper.offsetHeight,
                this.options.listenX && (this.wrapperWidth = this.wrapper.clientWidth,
                    this.indicatorWidth = Math.max(Math.round(this.wrapperWidth * this.wrapperWidth / (this.scroller.scrollerWidth || this.wrapperWidth || 1)), 8),
                    this.indicatorStyle.width = this.indicatorWidth + "px",
                    this.maxPosX = this.wrapperWidth - this.indicatorWidth,
                    this.minBoundaryX = 0,
                    this.maxBoundaryX = this.maxPosX,
                    this.sizeRatioX = this.options.speedRatioX || this.scroller.maxScrollX && this.maxPosX / this.scroller.maxScrollX),
                this.options.listenY && (this.wrapperHeight = this.wrapper.clientHeight,
                    this.indicatorHeight = Math.max(Math.round(this.wrapperHeight * this.wrapperHeight / (this.scroller.scrollerHeight || this.wrapperHeight || 1)), 8),
                    this.indicatorStyle.height = this.indicatorHeight + "px",
                    this.maxPosY = this.wrapperHeight - this.indicatorHeight,
                    this.minBoundaryY = 0,
                    this.maxBoundaryY = this.maxPosY,
                    this.sizeRatioY = this.options.speedRatioY || this.scroller.maxScrollY && this.maxPosY / this.scroller.maxScrollY),
                    this.updatePosition()
            },
            updatePosition: function() {
                var a = this.options.listenX && Math.round(this.sizeRatioX * this.scroller.x) || 0
                    , b = this.options.listenY && Math.round(this.sizeRatioY * this.scroller.y) || 0;
                a < this.minBoundaryX ? (this.width = Math.max(this.indicatorWidth + a, 8),
                    this.indicatorStyle.width = this.width + "px",
                    a = this.minBoundaryX) : a > this.maxBoundaryX ? (this.width = Math.max(this.indicatorWidth - (a - this.maxPosX), 8),
                    this.indicatorStyle.width = this.width + "px",
                    a = this.maxPosX + this.indicatorWidth - this.width) : this.width != this.indicatorWidth && (this.width = this.indicatorWidth,
                        this.indicatorStyle.width = this.width + "px"),
                    b < this.minBoundaryY ? (this.height = Math.max(this.indicatorHeight + 3 * b, 8),
                        this.indicatorStyle.height = this.height + "px",
                        b = this.minBoundaryY) : b > this.maxBoundaryY ? (this.height = Math.max(this.indicatorHeight - 3 * (b - this.maxPosY), 8),
                        this.indicatorStyle.height = this.height + "px",
                        b = this.maxPosY + this.indicatorHeight - this.height) : this.height != this.indicatorHeight && (this.height = this.indicatorHeight,
                            this.indicatorStyle.height = this.height + "px"),
                    this.x = a,
                    this.y = b,
                    this.indicatorStyle.webkitTransform = this.scroller._getTranslateStr(a, b)
            },
            fade: function(a, b) {
                if (!b || this.visible) {
                    clearTimeout(this.fadeTimeout),
                        this.fadeTimeout = null;
                    var c = a ? 250 : 500
                        , d = a ? 0 : 300;
                    a = a ? "1" : "0",
                        this.wrapperStyle.webkitTransitionDuration = c + "ms",
                        this.fadeTimeout = setTimeout(function(a) {
                            this.wrapperStyle.opacity = a,
                                this.visible = +a
                        }
                            .bind(this, a), d)
                }
            }
        },
            a.Scroll = l,
            a.fn.scroll = function(b) {
                var c = [];
                return this.each(function() {
                    var d = null
                        , e = this
                        , f = e.getAttribute("data-scroll");
                    if (f)
                        d = a.data[f];
                    else {
                        f = ++a.uuid;
                        var g = a.extend({}, b);
                        e.classList.contains("mui-segmented-control") && (g = a.extend(g, {
                            scrollY: !1,
                            scrollX: !0,
                            indicators: !1,
                            snap: ".mui-control-item"
                        })),
                            a.data[f] = d = new l(e,g),
                            e.setAttribute("data-scroll", f)
                    }
                    c.push(d)
                }),
                    1 === c.length ? c[0] : c
            }
    }(mui, window, document),
    function(a, b, c, d) {
        var e = "mui-visibility"
            , f = "mui-hidden"
            , g = a.Scroll.extend(a.extend({
            handleEvent: function(a) {
                this._super(a),
                "scrollbottom" === a.type && a.target === this.scroller && this._scrollbottom()
            },
            _scrollbottom: function() {
                this.pulldown || this.loading || (this.pulldown = !1,
                    this._initPullupRefresh(),
                    this.pullupLoading())
            },
            _start: function(a) {
                a.touches && a.touches.length && a.touches[0].clientX > 30 && a.target && !this._preventDefaultException(a.target, this.options.preventDefaultException) && a.preventDefault(),
                this.loading || (this.pulldown = this.pullPocket = this.pullCaption = this.pullLoading = !1),
                    this._super(a)
            },
            _drag: function(a) {
                this._super(a),
                !this.pulldown && !this.loading && this.topPocket && "down" === a.detail.direction && this.y >= 0 && this._initPulldownRefresh(),
                this.pulldown && this._setCaption(this.y > this.options.down.height ? this.options.down.contentover : this.options.down.contentdown)
            },
            _reLayout: function() {
                this.hasVerticalScroll = !0,
                    this._super()
            },
            resetPosition: function(a) {
                if (this.pulldown) {
                    if (this.y >= this.options.down.height)
                        return this.pulldownLoading(d, a || 0),
                            !0;
                    !this.loading && this.topPocket.classList.remove(e)
                }
                return this._super(a)
            },
            pulldownLoading: function(a, b) {
                if ("undefined" == typeof a && (a = this.options.down.height),
                        this.scrollTo(0, a, b, this.options.bounceEasing),
                        !this.loading) {
                    this._initPulldownRefresh(),
                        this._setCaption(this.options.down.contentrefresh),
                        this.loading = !0,
                        this.indicators.map(function(a) {
                            a.fade(0)
                        });
                    var c = this.options.down.callback;
                    c && c.call(this)
                }
            },
            endPulldownToRefresh: function() {
                var a = this;
                a.topPocket && a.loading && this.pulldown && (a.scrollTo(0, 0, a.options.bounceTime, a.options.bounceEasing),
                    a.loading = !1,
                    a._setCaption(a.options.down.contentdown, !0),
                    setTimeout(function() {
                        a.loading || a.topPocket.classList.remove(e)
                    }, 350))
            },
            pullupLoading: function(a, b, c) {
                b = b || 0,
                    this.scrollTo(b, this.maxScrollY, c, this.options.bounceEasing),
                this.loading || (this._initPullupRefresh(),
                    this._setCaption(this.options.up.contentrefresh),
                    this.indicators.map(function(a) {
                        a.fade(0)
                    }),
                    this.loading = !0,
                    a = a || this.options.up.callback,
                a && a.call(this))
            },
            endPullupToRefresh: function(a) {
                var b = this;
                b.bottomPocket && (b.loading = !1,
                    a ? (this.finished = !0,
                        b._setCaption(b.options.up.contentnomore),
                        b.wrapper.removeEventListener("scrollbottom", b)) : (b._setCaption(b.options.up.contentdown),
                    b.loading || b.bottomPocket.classList.remove(e)))
            },
            disablePullupToRefresh: function() {
                this._initPullupRefresh(),
                    this.bottomPocket.className = "mui-pull-bottom-pocket " + f,
                    this.wrapper.removeEventListener("scrollbottom", this)
            },
            enablePullupToRefresh: function() {
                this._initPullupRefresh(),
                    this.bottomPocket.classList.remove(f),
                    this._setCaption(this.options.up.contentdown),
                    this.wrapper.addEventListener("scrollbottom", this)
            },
            refresh: function(a) {
                a && this.finished && (this.enablePullupToRefresh(),
                    this.finished = !1),
                    this._super()
            }
        }, a.PullRefresh));
        a.fn.pullRefresh = function(b) {
            if (1 === this.length) {
                var c = this[0]
                    , d = null;
                b = b || {};
                var e = c.getAttribute("data-pullrefresh");
                return e ? d = a.data[e] : (e = ++a.uuid,
                    a.data[e] = d = new g(c,b),
                    c.setAttribute("data-pullrefresh", e)),
                    b.down && b.down.auto ? d.pulldownLoading(b.down.autoY) : b.up && b.up.auto && d.pullupLoading(),
                    d
            }
        }
    }(mui, window, document),
    function(a, b) {
        var c = "mui-slider"
            , d = "mui-slider-group"
            , e = "mui-slider-loop"
            , f = "mui-action-previous"
            , g = "mui-action-next"
            , h = "mui-slider-item"
            , i = "mui-active"
            , j = "." + h
            , k = ".mui-slider-progress-bar"
            , l = a.Slider = a.Scroll.extend({
            init: function(b, c) {
                this._super(b, a.extend(!0, {
                    fingers: 1,
                    interval: 0,
                    scrollY: !1,
                    scrollX: !0,
                    indicators: !1,
                    scrollTime: 1e3,
                    startX: !1,
                    slideTime: 0,
                    snap: j
                }, c)),
                    this.options.startX
            },
            _init: function() {
                this._reInit(),
                this.scroller && (this.scrollerStyle = this.scroller.style,
                    this.progressBar = this.wrapper.querySelector(k),
                this.progressBar && (this.progressBarWidth = this.progressBar.offsetWidth,
                    this.progressBarStyle = this.progressBar.style),
                    this._super(),
                    this._initTimer())
            },
            _triggerSlide: function() {
                var b = this;
                b.isInTransition = !1;
                b.currentPage;
                b.slideNumber = b._fixedSlideNumber(),
                b.loop && (0 === b.slideNumber ? b.setTranslate(b.pages[1][0].x, 0) : b.slideNumber === b.itemLength - 3 && b.setTranslate(b.pages[b.itemLength - 2][0].x, 0)),
                b.lastSlideNumber != b.slideNumber && (b.lastSlideNumber = b.slideNumber,
                    b.lastPage = b.currentPage,
                    a.trigger(b.wrapper, "slide", {
                        slideNumber: b.slideNumber
                    })),
                    b._initTimer()
            },
            _handleSlide: function(b) {
                var c = this;
                if (b.target === c.wrapper) {
                    var d = b.detail;
                    d.slideNumber = d.slideNumber || 0;
                    for (var e = c.scroller.querySelectorAll(j), f = [], g = 0, h = e.length; h > g; g++) {
                        var k = e[g];
                        k.parentNode === c.scroller && f.push(k)
                    }
                    var l = d.slideNumber;
                    if (c.loop && (l += 1),
                            !c.wrapper.classList.contains("mui-segmented-control"))
                        for (var g = 0, h = f.length; h > g; g++) {
                            var k = f[g];
                            k.parentNode === c.scroller && (g === l ? k.classList.add(i) : k.classList.remove(i))
                        }
                    var m = c.wrapper.querySelector(".mui-slider-indicator");
                    if (m) {
                        m.getAttribute("data-scroll") && a(m).scroll().gotoPage(d.slideNumber);
                        var n = m.querySelectorAll(".mui-indicator");
                        if (n.length > 0)
                            for (var g = 0, h = n.length; h > g; g++)
                                n[g].classList[g === d.slideNumber ? "add" : "remove"](i);
                        else {
                            var o = m.querySelector(".mui-number span");
                            if (o)
                                o.innerText = d.slideNumber + 1;
                            else
                                for (var p = m.querySelectorAll(".mui-control-item"), g = 0, h = p.length; h > g; g++)
                                    p[g].classList[g === d.slideNumber ? "add" : "remove"](i)
                        }
                    }
                    b.stopPropagation()
                }
            },
            _handleTabShow: function(a) {
                var b = this;
                b.gotoItem(a.detail.tabNumber || 0, b.options.slideTime)
            },
            _handleIndicatorTap: function(a) {
                var b = this
                    , c = a.target;
                (c.classList.contains(f) || c.classList.contains(g)) && (b[c.classList.contains(f) ? "prevItem" : "nextItem"](),
                    a.stopPropagation())
            },
            _initEvent: function(b) {
                var c = this;
                c._super(b);
                var d = b ? "removeEventListener" : "addEventListener";
                c.wrapper[d]("slide", this),
                    c.wrapper[d](a.eventName("shown", "tab"), this)
            },
            handleEvent: function(b) {
                switch (this._super(b),
                    b.type) {
                    case "slide":
                        this._handleSlide(b);
                        break;
                    case a.eventName("shown", "tab"):
                        ~this.snaps.indexOf(b.target) && this._handleTabShow(b)
                }
            },
            _scrollend: function(a) {
                this._super(a),
                    this._triggerSlide(a)
            },
            _drag: function(a) {
                this._super(a);
                var c = a.detail.direction;
                if ("left" === c || "right" === c) {
                    var d = this.wrapper.getAttribute("data-slidershowTimer");
                    d && b.clearTimeout(d),
                        a.stopPropagation()
                }
            },
            _initTimer: function() {
                var a = this
                    , c = a.wrapper
                    , d = a.options.interval
                    , e = c.getAttribute("data-slidershowTimer");
                e && b.clearTimeout(e),
                d && (e = b.setTimeout(function() {
                    c && ((c.offsetWidth || c.offsetHeight) && a.nextItem(!0),
                        a._initTimer())
                }, d),
                    c.setAttribute("data-slidershowTimer", e))
            },
            _fixedSlideNumber: function(a) {
                a = a || this.currentPage;
                var b = a.pageX;
                return this.loop && (b = 0 === a.pageX ? this.itemLength - 3 : a.pageX === this.itemLength - 1 ? 0 : a.pageX - 1),
                    b
            },
            _reLayout: function() {
                this.hasHorizontalScroll = !0,
                    this.loop = this.scroller.classList.contains(e),
                    this._super()
            },
            _getScroll: function() {
                var b = a.parseTranslateMatrix(a.getStyles(this.scroller, "webkitTransform"));
                return b ? b.x : 0
            },
            _transitionEnd: function(b) {
                b.target === this.scroller && this.isInTransition && (this._transitionTime(),
                    this.isInTransition = !1,
                    a.trigger(this.wrapper, "scrollend", this))
            },
            _flick: function(a) {
                if (this.moved) {
                    var b = a.detail
                        , c = b.direction;
                    this._clearRequestAnimationFrame(),
                        this.isInTransition = !0,
                        "flick" === a.type ? (b.deltaTime < 200 && (this.x = this._getPage(this.slideNumber + ("right" === c ? -1 : 1), !0).x),
                            this.resetPosition(this.options.bounceTime)) : "dragend" !== a.type || b.flick || this.resetPosition(this.options.bounceTime),
                        a.stopPropagation()
                }
            },
            _initSnap: function() {
                if (this.scrollerWidth = this.itemLength * this.scrollerWidth,
                        this.maxScrollX = Math.min(this.wrapperWidth - this.scrollerWidth, 0),
                        this._super(),
                        this.currentPage.x)
                    this.slideNumber = this._fixedSlideNumber(),
                        this.lastSlideNumber = "undefined" == typeof this.lastSlideNumber ? this.slideNumber : this.lastSlideNumber;
                else {
                    var a = this.pages[this.loop ? 1 : 0];
                    if (a = a || this.pages[0],
                            !a)
                        return;
                    this.currentPage = a[0],
                        this.slideNumber = 0,
                        this.lastSlideNumber = "undefined" == typeof this.lastSlideNumber ? 0 : this.lastSlideNumber
                }
                this.options.startX = this.currentPage.x || 0
            },
            _getSnapX: function(a) {
                return Math.max(-a, this.maxScrollX)
            },
            _getPage: function(a, b) {
                return this.loop ? a > this.itemLength - (b ? 2 : 3) ? (a = 1,
                    time = 0) : (b ? -1 : 0) > a ? (a = this.itemLength - 2,
                    time = 0) : a += 1 : (b || (a > this.itemLength - 1 ? (a = 0,
                    time = 0) : 0 > a && (a = this.itemLength - 1,
                        time = 0)),
                    a = Math.min(Math.max(0, a), this.itemLength - 1)),
                    this.pages[a][0]
            },
            _gotoItem: function(b, c) {
                this.currentPage = this._getPage(b, !0),
                    this.scrollTo(this.currentPage.x, 0, c, this.options.scrollEasing),
                0 === c && a.trigger(this.wrapper, "scrollend", this)
            },
            setTranslate: function(a, b) {
                this._super(a, b);
                var c = this.progressBar;
                c && (this.progressBarStyle.webkitTransform = this._getTranslateStr(-a * (this.progressBarWidth / this.wrapperWidth), 0))
            },
            resetPosition: function(a) {
                return a = a || 0,
                    this.x > 0 ? this.x = 0 : this.x < this.maxScrollX && (this.x = this.maxScrollX),
                    this.currentPage = this._nearestSnap(this.x),
                    this.scrollTo(this.currentPage.x, 0, a, this.options.scrollEasing),
                    !0
            },
            gotoItem: function(a, b) {
                this._gotoItem(a, "undefined" == typeof b ? this.options.scrollTime : b)
            },
            nextItem: function() {
                this._gotoItem(this.slideNumber + 1, this.options.scrollTime)
            },
            prevItem: function() {
                this._gotoItem(this.slideNumber - 1, this.options.scrollTime)
            },
            getSlideNumber: function() {
                return this.slideNumber || 0
            },
            _reInit: function() {
                for (var a = this.wrapper.querySelectorAll("." + d), b = 0, c = a.length; c > b; b++)
                    if (a[b].parentNode === this.wrapper) {
                        this.scroller = a[b];
                        break
                    }
                this.scrollerStyle = this.scroller && this.scroller.style,
                this.progressBar && (this.progressBarWidth = this.progressBar.offsetWidth,
                    this.progressBarStyle = this.progressBar.style)
            },
            refresh: function(b) {
                b ? (a.extend(this.options, b),
                    this._super(),
                    this._initTimer()) : this._super()
            },
            destroy: function() {
                this._initEvent(!0),
                    delete a.data[this.wrapper.getAttribute("data-slider")],
                    this.wrapper.setAttribute("data-slider", "")
            }
        });
        a.fn.slider = function(b) {
            var d = null;
            return this.each(function() {
                var e = this;
                if (this.classList.contains(c) || (e = this.querySelector("." + c)),
                    e && e.querySelector(j)) {
                    var f = e.getAttribute("data-slider");
                    f ? (d = a.data[f],
                    d && b && d.refresh(b)) : (f = ++a.uuid,
                        a.data[f] = d = new l(e,b),
                        e.setAttribute("data-slider", f))
                }
            }),
                d
        }
            ,
            a.ready(function() {
                a(".mui-slider").slider(),
                    a(".mui-scroll-wrapper.mui-slider-indicator.mui-segmented-control").scroll({
                        scrollY: !1,
                        scrollX: !0,
                        indicators: !1,
                        snap: ".mui-control-item"
                    })
            })
    }(mui, window),
    function(a, b) {
        a.os.plus && a.os.android && a.plusReady(function() {
            if (window.__NWin_Enable__ !== !1) {
                var c = "mui-plus-pullrefresh"
                    , d = "mui-visibility"
                    , e = "mui-hidden"
                    , f = "mui-block"
                    , g = "mui-pull-caption"
                    , h = "mui-pull-caption-down"
                    , i = "mui-pull-caption-refresh"
                    , j = "mui-pull-caption-nomore"
                    , k = a.Class.extend({
                    init: function(a, b) {
                        this.element = a,
                            this.options = b,
                            this.wrapper = this.scroller = a,
                            this._init(),
                            this._initPulldownRefreshEvent()
                    },
                    _init: function() {
                        var a = this;
                        window.addEventListener("dragup", a),
                            b.addEventListener("plusscrollbottom", a),
                            a.scrollInterval = window.setInterval(function() {
                                a.isScroll && !a.loading && window.pageYOffset + window.innerHeight + 10 >= b.documentElement.scrollHeight && (a.isScroll = !1,
                                a.bottomPocket && a.pullupLoading())
                            }, 100)
                    },
                    _initPulldownRefreshEvent: function() {
                        var b = this;
                        b.topPocket && b.options.webviewId && a.plusReady(function() {
                            var a = plus.webview.getWebviewById(b.options.webviewId);
                            if (a) {
                                b.options.webview = a;
                                var c = b.options.down
                                    , d = c.height;
                                a.addEventListener("close", function() {
                                    var a = b.options.webviewId && b.options.webviewId.replace(/\//g, "_");
                                    b.element.removeAttribute("data-pullrefresh-plus-" + a)
                                }),
                                    a.addEventListener("dragBounce", function(d) {
                                        switch (b.pulldown ? b.pullPocket.classList.add(f) : b._initPulldownRefresh(),
                                            d.status) {
                                            case "beforeChangeOffset":
                                                b._setCaption(c.contentdown);
                                                break;
                                            case "afterChangeOffset":
                                                b._setCaption(c.contentover);
                                                break;
                                            case "dragEndAfterChangeOffset":
                                                a.evalJS("mui&&mui.options.pullRefresh.down.callback()"),
                                                    b._setCaption(c.contentrefresh)
                                        }
                                    }, !1),
                                    a.setBounce({
                                        position: {
                                            top: 2 * d + "px"
                                        },
                                        changeoffset: {
                                            top: d + "px"
                                        }
                                    })
                            }
                        })
                    },
                    handleEvent: function(a) {
                        var b = this;
                        b.stopped || (b.isScroll = !1,
                        ("dragup" === a.type || "plusscrollbottom" === a.type) && (b.isScroll = !0,
                            setTimeout(function() {
                                b.isScroll = !1
                            }, 1e3)))
                    }
                }).extend(a.extend({
                    setStopped: function(a) {
                        this.stopped = !!a;
                        var b = plus.webview.currentWebview();
                        if (this.stopped)
                            b.setStyle({
                                bounce: "none"
                            }),
                                b.setBounce({
                                    position: {
                                        top: "none"
                                    }
                                });
                        else {
                            var c = this.options.down.height;
                            b.setStyle({
                                bounce: "vertical"
                            }),
                                b.setBounce({
                                    position: {
                                        top: 2 * c + "px"
                                    },
                                    changeoffset: {
                                        top: c + "px"
                                    }
                                })
                        }
                    },
                    pulldownLoading: function() {
                        a.plusReady(function() {
                            plus.webview.currentWebview().setBounce({
                                offset: {
                                    top: this.options.down.height + "px"
                                }
                            })
                        }
                            .bind(this))
                    },
                    _pulldownLoading: function() {
                        var b = this;
                        a.plusReady(function() {
                            var a = plus.webview.getWebviewById(b.options.webviewId);
                            a.setBounce({
                                offset: {
                                    top: b.options.down.height + "px"
                                }
                            })
                        })
                    },
                    endPulldownToRefresh: function() {
                        var a = plus.webview.currentWebview();
                        a.parent().evalJS("mui&&mui(document.querySelector('.mui-content')).pullRefresh('" + JSON.stringify({
                                webviewId: a.id
                            }) + "')._endPulldownToRefresh()")
                    },
                    _endPulldownToRefresh: function() {
                        var a = this;
                        a.topPocket && a.options.webview && (a.options.webview.endPullToRefresh(),
                            a.loading = !1,
                            a._setCaption(a.options.down.contentdown, !0),
                            setTimeout(function() {
                                a.loading || a.topPocket.classList.remove(f)
                            }, 350))
                    },
                    pullupLoading: function(a) {
                        var b = this;
                        b.isLoading || (b.isLoading = !0,
                            b.pulldown !== !1 ? b._initPullupRefresh() : this.pullPocket.classList.add(f),
                            setTimeout(function() {
                                b.pullLoading.classList.add(d),
                                    b.pullLoading.classList.remove(e),
                                    b.pullCaption.innerHTML = "",
                                    b.pullCaption.className = g + " " + i,
                                    b.pullCaption.innerHTML = b.options.up.contentrefresh,
                                    a = a || b.options.up.callback,
                                a && a.call(b)
                            }, 300))
                    },
                    endPullupToRefresh: function(a) {
                        var c = this;
                        c.pullLoading && (c.pullLoading.classList.remove(d),
                            c.pullLoading.classList.add(e),
                            c.isLoading = !1,
                            a ? (c.finished = !0,
                                c.pullCaption.className = g + " " + j,
                                c.pullCaption.innerHTML = c.options.up.contentnomore,
                                b.removeEventListener("plusscrollbottom", c),
                                window.removeEventListener("dragup", c)) : (c.pullCaption.className = g + " " + h,
                                c.pullCaption.innerHTML = c.options.up.contentdown))
                    },
                    disablePullupToRefresh: function() {
                        this._initPullupRefresh(),
                            this.bottomPocket.className = "mui-pull-bottom-pocket " + e,
                            window.removeEventListener("dragup", this)
                    },
                    enablePullupToRefresh: function() {
                        this._initPullupRefresh(),
                            this.bottomPocket.classList.remove(e),
                            this.pullCaption.className = g + " " + h,
                            this.pullCaption.innerHTML = this.options.up.contentdown,
                            b.addEventListener("plusscrollbottom", this),
                            window.addEventListener("dragup", this)
                    },
                    scrollTo: function(b, c, d) {
                        a.scrollTo(c, d)
                    },
                    scrollToBottom: function(c) {
                        a.scrollTo(b.documentElement.scrollHeight, c)
                    },
                    refresh: function(a) {
                        a && this.finished && (this.enablePullupToRefresh(),
                            this.finished = !1)
                    }
                }, a.PullRefresh));
                a.fn.pullRefresh = function(d) {
                    var e;
                    0 === this.length ? (e = b.createElement("div"),
                        e.className = "mui-content",
                        b.body.appendChild(e)) : e = this[0];
                    var f = d;
                    d = d || {},
                    "string" == typeof d && (d = a.parseJSON(d)),
                    !d.webviewId && (d.webviewId = plus.webview.currentWebview().id || plus.webview.currentWebview().getURL());
                    var g = null
                        , h = d.webviewId && d.webviewId.replace(/\//g, "_")
                        , i = e.getAttribute("data-pullrefresh-plus-" + h);
                    return i || "undefined" != typeof f ? (i ? g = a.data[i] : (i = ++a.uuid,
                        e.setAttribute("data-pullrefresh-plus-" + h, i),
                        b.body.classList.add(c),
                        a.data[i] = g = new k(e,d)),
                        d.down && d.down.auto ? g._pulldownLoading() : d.up && d.up.auto && g.pullupLoading(),
                        g) : !1
                }
            }
        })
    }(mui, document),
    function(a, b, c, d) {
        var e = "mui-off-canvas-left"
            , f = "mui-off-canvas-right"
            , g = "mui-off-canvas-backdrop"
            , h = "mui-off-canvas-wrap"
            , i = "mui-slide-in"
            , j = "mui-active"
            , k = "mui-transitioning"
            , l = ".mui-inner-wrap"
            , m = a.Class.extend({
            init: function(b, d) {
                this.wrapper = this.element = b,
                    this.scroller = this.wrapper.querySelector(l),
                    this.classList = this.wrapper.classList,
                this.scroller && (this.options = a.extend(!0, {
                    dragThresholdX: 10,
                    scale: .8,
                    opacity: .1,
                    preventDefaultException: {
                        tagName: /^(INPUT|TEXTAREA|BUTTON|SELECT|VIDEO)$/
                    }
                }, d),
                    c.body.classList.add("mui-fullscreen"),
                    this.refresh(),
                    this.initEvent())
            },
            _preventDefaultException: function(a, b) {
                for (var c in b)
                    if (b[c].test(a[c]))
                        return !0;
                return !1
            },
            refresh: function(a) {
                this.slideIn = this.classList.contains(i),
                    this.scalable = this.classList.contains("mui-scalable") && !this.slideIn,
                    this.scroller = this.wrapper.querySelector(l),
                    this.offCanvasLefts = this.wrapper.querySelectorAll("." + e),
                    this.offCanvasRights = this.wrapper.querySelectorAll("." + f),
                    a ? a.classList.contains(e) ? this.offCanvasLeft = a : a.classList.contains(f) && (this.offCanvasRight = a) : (this.offCanvasRight = this.wrapper.querySelector("." + f),
                        this.offCanvasLeft = this.wrapper.querySelector("." + e)),
                    this.offCanvasRightWidth = this.offCanvasLeftWidth = 0,
                    this.offCanvasLeftSlideIn = this.offCanvasRightSlideIn = !1,
                this.offCanvasRight && (this.offCanvasRightWidth = this.offCanvasRight.offsetWidth,
                    this.offCanvasRightSlideIn = this.slideIn && this.offCanvasRight.parentNode === this.wrapper),
                this.offCanvasLeft && (this.offCanvasLeftWidth = this.offCanvasLeft.offsetWidth,
                    this.offCanvasLeftSlideIn = this.slideIn && this.offCanvasLeft.parentNode === this.wrapper),
                    this.backdrop = this.scroller.querySelector("." + g),
                    this.options.dragThresholdX = this.options.dragThresholdX || 10,
                    this.visible = !1,
                    this.startX = null,
                    this.lastX = null,
                    this.offsetX = null,
                    this.lastTranslateX = null
            },
            handleEvent: function(b) {
                switch (b.type) {
                    case a.EVENT_START:
                        b.target && !this._preventDefaultException(b.target, this.options.preventDefaultException) && b.preventDefault();
                        break;
                    case "webkitTransitionEnd":
                        b.target === this.scroller && this._dispatchEvent();
                        break;
                    case "drag":
                        var c = b.detail;
                        this.startX ? this.lastX = c.center.x : (this.startX = c.center.x,
                            this.lastX = this.startX),
                        !this.isDragging && Math.abs(this.lastX - this.startX) > this.options.dragThresholdX && ("left" === c.direction || "right" === c.direction) && (this.slideIn ? (this.scroller = this.wrapper.querySelector(l),
                            this.classList.contains(j) ? this.offCanvasRight && this.offCanvasRight.classList.contains(j) ? (this.offCanvas = this.offCanvasRight,
                                this.offCanvasWidth = this.offCanvasRightWidth) : (this.offCanvas = this.offCanvasLeft,
                                this.offCanvasWidth = this.offCanvasLeftWidth) : "left" === c.direction && this.offCanvasRight ? (this.offCanvas = this.offCanvasRight,
                                this.offCanvasWidth = this.offCanvasRightWidth) : "right" === c.direction && this.offCanvasLeft ? (this.offCanvas = this.offCanvasLeft,
                                this.offCanvasWidth = this.offCanvasLeftWidth) : this.scroller = null) : this.classList.contains(j) ? "left" === c.direction ? (this.offCanvas = this.offCanvasLeft,
                            this.offCanvasWidth = this.offCanvasLeftWidth) : (this.offCanvas = this.offCanvasRight,
                            this.offCanvasWidth = this.offCanvasRightWidth) : "right" === c.direction ? (this.offCanvas = this.offCanvasLeft,
                            this.offCanvasWidth = this.offCanvasLeftWidth) : (this.offCanvas = this.offCanvasRight,
                            this.offCanvasWidth = this.offCanvasRightWidth),
                        this.offCanvas && this.scroller && (this.startX = this.lastX,
                            this.isDragging = !0,
                            a.gestures.session.lockDirection = !0,
                            a.gestures.session.startDirection = c.direction,
                            this.offCanvas.classList.remove(k),
                            this.scroller.classList.remove(k),
                            this.offsetX = this.getTranslateX(),
                            this._initOffCanvasVisible())),
                        this.isDragging && (this.updateTranslate(this.offsetX + (this.lastX - this.startX)),
                            c.gesture.preventDefault(),
                            b.stopPropagation());
                        break;
                    case "dragend":
                        if (this.isDragging) {
                            var c = b.detail
                                , d = c.direction;
                            this.isDragging = !1,
                                this.offCanvas.classList.add(k),
                                this.scroller.classList.add(k);
                            var e = 0
                                , f = this.getTranslateX();
                            if (this.slideIn) {
                                if (e = f >= 0 ? this.offCanvasRightWidth && f / this.offCanvasRightWidth || 0 : this.offCanvasLeftWidth && f / this.offCanvasLeftWidth || 0,
                                        "right" === d && 0 >= e && (e >= -.5 || c.swipe) ? this.openPercentage(100) : "right" === d && e > 0 && (e >= .5 || c.swipe) ? this.openPercentage(0) : "right" === d && -.5 >= e ? this.openPercentage(0) : "right" === d && e > 0 && .5 >= e ? this.openPercentage(-100) : "left" === d && e >= 0 && (.5 >= e || c.swipe) ? this.openPercentage(-100) : "left" === d && 0 > e && (-.5 >= e || c.swipe) ? this.openPercentage(0) : "left" === d && e >= .5 ? this.openPercentage(0) : "left" === d && e >= -.5 && 0 > e ? this.openPercentage(100) : this.openPercentage(0),
                                    1 === e || -1 === e || 0 === e)
                                    return void this._dispatchEvent()
                            } else {
                                if (e = f >= 0 ? this.offCanvasLeftWidth && f / this.offCanvasLeftWidth || 0 : this.offCanvasRightWidth && f / this.offCanvasRightWidth || 0,
                                    0 === e)
                                    return this.openPercentage(0),
                                        void this._dispatchEvent();
                                "right" === d && e >= 0 && (e >= .5 || c.swipe) ? this.openPercentage(100) : "right" === d && 0 > e && (e > -.5 || c.swipe) ? this.openPercentage(0) : "right" === d && e > 0 && .5 > e ? this.openPercentage(0) : "right" === d && .5 > e ? this.openPercentage(-100) : "left" === d && 0 >= e && (-.5 >= e || c.swipe) ? this.openPercentage(-100) : "left" === d && e > 0 && (.5 >= e || c.swipe) ? this.openPercentage(0) : "left" === d && 0 > e && e >= -.5 ? this.openPercentage(0) : "left" === d && e > .5 ? this.openPercentage(100) : this.openPercentage(0),
                                (1 === e || -1 === e) && this._dispatchEvent()
                            }
                        }
                }
            },
            _dispatchEvent: function() {
                this.classList.contains(j) ? a.trigger(this.wrapper, "shown", this) : a.trigger(this.wrapper, "hidden", this)
            },
            _initOffCanvasVisible: function() {
                this.visible || (this.visible = !0,
                this.offCanvasLeft && (this.offCanvasLeft.style.visibility = "visible"),
                this.offCanvasRight && (this.offCanvasRight.style.visibility = "visible"))
            },
            initEvent: function() {
                var b = this;
                b.backdrop && b.backdrop.addEventListener("tap", function(a) {
                    b.close(),
                        a.detail.gesture.preventDefault()
                }),
                this.classList.contains("mui-draggable") && (this.wrapper.addEventListener(a.EVENT_START, this),
                    this.wrapper.addEventListener("drag", this),
                    this.wrapper.addEventListener("dragend", this)),
                    this.wrapper.addEventListener("webkitTransitionEnd", this)
            },
            openPercentage: function(a) {
                var b = a / 100;
                this.slideIn ? (this.offCanvasLeft && a >= 0 ? (b = 0 === b ? -1 : 0,
                    this.updateTranslate(this.offCanvasLeftWidth * b),
                    this.offCanvasLeft.classList[0 !== a ? "add" : "remove"](j)) : this.offCanvasRight && 0 >= a && (b = 0 === b ? 1 : 0,
                        this.updateTranslate(this.offCanvasRightWidth * b),
                        this.offCanvasRight.classList[0 !== a ? "add" : "remove"](j)),
                    this.classList[0 !== a ? "add" : "remove"](j)) : (this.offCanvasLeft && a >= 0 ? (this.updateTranslate(this.offCanvasLeftWidth * b),
                    this.offCanvasLeft.classList[0 !== b ? "add" : "remove"](j)) : this.offCanvasRight && 0 >= a && (this.updateTranslate(this.offCanvasRightWidth * b),
                        this.offCanvasRight.classList[0 !== b ? "add" : "remove"](j)),
                    this.classList[0 !== b ? "add" : "remove"](j))
            },
            updateTranslate: function(b) {
                if (b !== this.lastTranslateX) {
                    if (this.slideIn) {
                        if (this.offCanvas.classList.contains(f)) {
                            if (0 > b)
                                return void this.setTranslateX(0);
                            if (b > this.offCanvasRightWidth)
                                return void this.setTranslateX(this.offCanvasRightWidth)
                        } else {
                            if (b > 0)
                                return void this.setTranslateX(0);
                            if (b < -this.offCanvasLeftWidth)
                                return void this.setTranslateX(-this.offCanvasLeftWidth)
                        }
                        this.setTranslateX(b)
                    } else {
                        if (!this.offCanvasLeft && b > 0 || !this.offCanvasRight && 0 > b)
                            return void this.setTranslateX(0);
                        if (this.leftShowing && b > this.offCanvasLeftWidth)
                            return void this.setTranslateX(this.offCanvasLeftWidth);
                        if (this.rightShowing && b < -this.offCanvasRightWidth)
                            return void this.setTranslateX(-this.offCanvasRightWidth);
                        this.setTranslateX(b),
                            b >= 0 ? (this.leftShowing = !0,
                                this.rightShowing = !1,
                            b > 0 && (this.offCanvasLeft && a.each(this.offCanvasLefts, function(a, b) {
                                b === this.offCanvasLeft ? this.offCanvasLeft.style.zIndex = 0 : b.style.zIndex = -1
                            }
                                .bind(this)),
                            this.offCanvasRight && (this.offCanvasRight.style.zIndex = -1))) : (this.rightShowing = !0,
                                this.leftShowing = !1,
                            this.offCanvasRight && a.each(this.offCanvasRights, function(a, b) {
                                b === this.offCanvasRight ? b.style.zIndex = 0 : b.style.zIndex = -1
                            }
                                .bind(this)),
                            this.offCanvasLeft && (this.offCanvasLeft.style.zIndex = -1))
                    }
                    this.lastTranslateX = b
                }
            },
            setTranslateX: a.animationFrame(function(a) {
                if (this.scroller)
                    if (this.scalable && this.offCanvas.parentNode === this.wrapper) {
                        var b = Math.abs(a) / this.offCanvasWidth
                            , c = 1 - (1 - this.options.scale) * b
                            , d = this.options.scale + (1 - this.options.scale) * b
                            , f = (1 - (1 - this.options.opacity) * b,
                        this.options.opacity + (1 - this.options.opacity) * b);
                        this.offCanvas.classList.contains(e) ? (this.offCanvas.style.webkitTransformOrigin = "-100%",
                            this.scroller.style.webkitTransformOrigin = "left") : (this.offCanvas.style.webkitTransformOrigin = "200%",
                            this.scroller.style.webkitTransformOrigin = "right"),
                            this.offCanvas.style.opacity = f,
                            this.offCanvas.style.webkitTransform = "translate3d(0,0,0) scale(" + d + ")",
                            this.scroller.style.webkitTransform = "translate3d(" + a + "px,0,0) scale(" + c + ")"
                    } else
                        this.slideIn ? this.offCanvas.style.webkitTransform = "translate3d(" + a + "px,0,0)" : this.scroller.style.webkitTransform = "translate3d(" + a + "px,0,0)"
            }),
            getTranslateX: function() {
                if (this.offCanvas) {
                    var b = this.slideIn ? this.offCanvas : this.scroller
                        , c = a.parseTranslateMatrix(a.getStyles(b, "webkitTransform"));
                    return c && c.x || 0
                }
                return 0
            },
            isShown: function(a) {
                var b = !1;
                if (this.slideIn)
                    b = "left" === a ? this.classList.contains(j) && this.wrapper.querySelector("." + e + "." + j) : "right" === a ? this.classList.contains(j) && this.wrapper.querySelector("." + f + "." + j) : this.classList.contains(j) && (this.wrapper.querySelector("." + e + "." + j) || this.wrapper.querySelector("." + f + "." + j));
                else {
                    var c = this.getTranslateX();
                    b = "right" === a ? this.classList.contains(j) && 0 > c : "left" === a ? this.classList.contains(j) && c > 0 : this.classList.contains(j) && 0 !== c
                }
                return b
            },
            close: function() {
                this._initOffCanvasVisible(),
                    this.offCanvas = this.wrapper.querySelector("." + f + "." + j) || this.wrapper.querySelector("." + e + "." + j),
                    this.offCanvasWidth = this.offCanvas.offsetWidth,
                this.scroller && (this.offCanvas.offsetHeight,
                    this.offCanvas.classList.add(k),
                    this.scroller.classList.add(k),
                    this.openPercentage(0))
            },
            show: function(a) {
                return this._initOffCanvasVisible(),
                    this.isShown(a) ? !1 : (a || (a = this.wrapper.querySelector("." + f) ? "right" : "left"),
                        "right" === a ? (this.offCanvas = this.offCanvasRight,
                            this.offCanvasWidth = this.offCanvasRightWidth) : (this.offCanvas = this.offCanvasLeft,
                            this.offCanvasWidth = this.offCanvasLeftWidth),
                    this.scroller && (this.offCanvas.offsetHeight,
                        this.offCanvas.classList.add(k),
                        this.scroller.classList.add(k),
                        this.openPercentage("left" === a ? 100 : -100)),
                        !0)
            },
            toggle: function(a) {
                var b = a;
                a && a.classList && (b = a.classList.contains(e) ? "left" : "right",
                    this.refresh(a)),
                this.show(b) || this.close()
            }
        })
            , n = function(a) {
            if (parentNode = a.parentNode,
                    parentNode) {
                if (parentNode.classList.contains(h))
                    return parentNode;
                if (parentNode = parentNode.parentNode,
                        parentNode.classList.contains(h))
                    return parentNode
            }
        }
            , o = function(b, d) {
            if ("A" === d.tagName && d.hash) {
                var e = c.getElementById(d.hash.replace("#", ""));
                if (e) {
                    var f = n(e);
                    if (f)
                        return a.targets._container = f,
                            e
                }
            }
            return !1
        };
        a.registerTarget({
            name: d,
            index: 60,
            handle: o,
            target: !1,
            isReset: !1,
            isContinue: !0
        }),
            b.addEventListener("tap", function(b) {
                if (a.targets.offcanvas)
                    for (var d = b.target; d && d !== c; d = d.parentNode)
                        if ("A" === d.tagName && d.hash && d.hash === "#" + a.targets.offcanvas.id) {
                            b.detail && b.detail.gesture && b.detail.gesture.preventDefault(),
                                a(a.targets._container).offCanvas().toggle(a.targets.offcanvas),
                                a.targets.offcanvas = a.targets._container = null;
                            break
                        }
            }),
            a.fn.offCanvas = function(b) {
                var c = [];
                return this.each(function() {
                    var d = null
                        , e = this;
                    e.classList.contains(h) || (e = n(e));
                    var f = e.getAttribute("data-offCanvas");
                    f ? d = a.data[f] : (f = ++a.uuid,
                        a.data[f] = d = new m(e,b),
                        e.setAttribute("data-offCanvas", f)),
                    ("show" === b || "close" === b || "toggle" === b) && d.toggle(),
                        c.push(d)
                }),
                    1 === c.length ? c[0] : c
            }
            ,
            a.ready(function() {
                a(".mui-off-canvas-wrap").offCanvas()
            })
    }(mui, window, document, "offcanvas"),
    function(a, b) {
        var c = "mui-action"
            , d = function(a, b) {
            var d = b.className || "";
            return "string" != typeof d && (d = ""),
                d && ~d.indexOf(c) ? (b.classList.contains("mui-action-back") && a.preventDefault(),
                    b) : !1
        };
        a.registerTarget({
            name: b,
            index: 50,
            handle: d,
            target: !1,
            isContinue: !0
        })
    }(mui, "action"),
    function(a, b, c, d) {
        var e = "mui-modal"
            , f = function(a, b) {
            if ("A" === b.tagName && b.hash) {
                var d = c.getElementById(b.hash.replace("#", ""));
                if (d && d.classList.contains(e))
                    return d
            }
            return !1
        };
        a.registerTarget({
            name: d,
            index: 50,
            handle: f,
            target: !1,
            isReset: !1,
            isContinue: !0
        }),
            b.addEventListener("tap", function(b) {
                a.targets.modal && (b.detail.gesture.preventDefault(),
                    a.targets.modal.classList.toggle("mui-active"))
            })
    }(mui, window, document, "modal"),
    function(a, b, c, d) {
        var e = "mui-popover"
            , f = "mui-popover-arrow"
            , g = "mui-popover-action"
            , h = "mui-backdrop"
            , i = "mui-bar-popover"
            , j = "mui-bar-backdrop"
            , k = "mui-backdrop-action"
            , l = "mui-active"
            , m = "mui-bottom"
            , n = function(b, d) {
            if ("A" === d.tagName && d.hash) {
                if (a.targets._popover = c.getElementById(d.hash.replace("#", "")),
                    a.targets._popover && a.targets._popover.classList.contains(e))
                    return d;
                a.targets._popover = null
            }
            return !1
        };
        a.registerTarget({
            name: d,
            index: 60,
            handle: n,
            target: !1,
            isReset: !1,
            isContinue: !0
        });
        var o, p = function(b) {
            this.removeEventListener("webkitTransitionEnd", p),
                this.addEventListener(a.EVENT_MOVE, a.preventDefault),
                a.trigger(this, "shown", this)
        }, q = function(b) {
            u(this, "none"),
                this.removeEventListener("webkitTransitionEnd", q),
                this.removeEventListener(a.EVENT_MOVE, a.preventDefault),
                a.trigger(this, "hidden", this)
        }, r = function() {
            var b = c.createElement("div");
            return b.classList.add(h),
                b.addEventListener(a.EVENT_MOVE, a.preventDefault),
                b.addEventListener("tap", function(b) {
                    var c = a.targets._popover;
                    c && (c.addEventListener("webkitTransitionEnd", q),
                        c.classList.remove(l),
                        s(c))
                }),
                b
        }(), s = function(b) {
            r.setAttribute("style", "opacity:0"),
                a.targets.popover = a.targets._popover = null,
                o = a.later(function() {
                    !b.classList.contains(l) && r.parentNode && r.parentNode === c.body && c.body.removeChild(r)
                }, 350)
        };
        b.addEventListener("tap", function(b) {
            if (a.targets.popover) {
                for (var d = !1, e = b.target; e && e !== c; e = e.parentNode)
                    e === a.targets.popover && (d = !0);
                d && (b.detail.gesture.preventDefault(),
                    t(a.targets._popover, a.targets.popover))
            }
        });
        var t = function(a, b, d) {
            if (!("show" === d && a.classList.contains(l) || "hide" === d && !a.classList.contains(l))) {
                o && o.cancel(),
                    a.removeEventListener("webkitTransitionEnd", p),
                    a.removeEventListener("webkitTransitionEnd", q),
                    r.classList.remove(j),
                    r.classList.remove(k);
                var e = c.querySelector(".mui-popover.mui-active");
                if (e && (e.addEventListener("webkitTransitionEnd", q),
                        e.classList.remove(l),
                    a === e))
                    return void s(e);
                var f = !1;
                (a.classList.contains(i) || a.classList.contains(g)) && (a.classList.contains(g) ? (f = !0,
                    r.classList.add(k)) : r.classList.add(j)),
                    u(a, "block"),
                    a.offsetHeight,
                    a.classList.add(l),
                    r.setAttribute("style", ""),
                    c.body.appendChild(r),
                    v(a, b, f),
                    r.classList.add(l),
                    a.addEventListener("webkitTransitionEnd", p)
            }
        }
            , u = function(a, b, c, d) {
            var e = a.style;
            "undefined" != typeof b && (e.display = b),
            "undefined" != typeof c && (e.top = c + "px"),
            "undefined" != typeof d && (e.left = d + "px")
        }
            , v = function(d, e, h) {
            if (d && e) {
                if (h)
                    return void u(d, "block");
                var i = b.innerWidth
                    , j = b.innerHeight
                    , k = d.offsetWidth
                    , l = d.offsetHeight
                    , n = e.offsetWidth
                    , o = e.offsetHeight
                    , p = a.offset(e)
                    , q = d.querySelector("." + f);
                q || (q = c.createElement("div"),
                    q.className = f,
                    d.appendChild(q));
                var r = q && q.offsetWidth / 2 || 0
                    , s = 0
                    , t = 0
                    , v = 0
                    , w = 0
                    , x = d.classList.contains(g) ? 0 : 5
                    , y = "top";
                l + r < p.top - b.pageYOffset ? s = p.top - l - r : l + r < j - (p.top - b.pageYOffset) - o ? (y = "bottom",
                    s = p.top + o + r) : (y = "middle",
                    s = Math.max((j - l) / 2 + b.pageYOffset, 0),
                    t = Math.max((i - k) / 2 + b.pageXOffset, 0)),
                    "top" === y || "bottom" === y ? (t = n / 2 + p.left - k / 2,
                        v = t,
                    x > t && (t = x),
                    t + k > i && (t = i - k - x),
                    q && ("top" === y ? q.classList.add(m) : q.classList.remove(m),
                        v -= t,
                        w = k / 2 - r / 2 + v,
                        w = Math.max(Math.min(w, k - 2 * r - 6), 6),
                        q.setAttribute("style", "left:" + w + "px"))) : "middle" === y && q.setAttribute("style", "display:none"),
                    u(d, "block", s, t)
            }
        };
        a.createMask = function(b) {
            var d = c.createElement("div");
            d.classList.add(h),
                d.addEventListener(a.EVENT_MOVE, a.preventDefault),
                d.addEventListener("tap", function() {
                    e.close()
                });
            var e = [d];
            return e._show = !1,
                e.show = function() {
                    return e._show = !0,
                        d.setAttribute("style", "opacity:1"),
                        c.body.appendChild(d),
                        e
                }
                ,
                e._remove = function() {
                    return e._show && (e._show = !1,
                        d.setAttribute("style", "opacity:0"),
                        a.later(function() {
                            var a = c.body;
                            d.parentNode === a && a.removeChild(d)
                        }, 350)),
                        e
                }
                ,
                e.close = function() {
                    b ? b() !== !1 && e._remove() : e._remove()
                }
                ,
                e
        }
            ,
            a.fn.popover = function() {
                var b = arguments;
                this.each(function() {
                    a.targets._popover = this,
                    ("show" === b[0] || "hide" === b[0] || "toggle" === b[0]) && t(this, b[1], b[0])
                })
            }
    }(mui, window, document, "popover"),
    function(a, b, c, d, e) {
        var f = "mui-control-item"
            , g = "mui-segmented-control"
            , h = "mui-segmented-control-vertical"
            , i = "mui-control-content"
            , j = "mui-bar-tab"
            , k = "mui-tab-item"
            , l = function(a, b) {
            return b.classList && (b.classList.contains(f) || b.classList.contains(k)) ? (b.parentNode && b.parentNode.classList && b.parentNode.classList.contains(h) || a.preventDefault(),
                b) : !1
        };
        a.registerTarget({
            name: d,
            index: 80,
            handle: l,
            target: !1
        }),
            b.addEventListener("tap", function(b) {
                var e = a.targets.tab;
                if (e) {
                    for (var h, l, m, n = "mui-active", o = "." + n, p = e.parentNode; p && p !== c; p = p.parentNode) {
                        if (p.classList.contains(g)) {
                            h = p.querySelector(o + "." + f);
                            break
                        }
                        p.classList.contains(j) && (h = p.querySelector(o + "." + k))
                    }
                    h && h.classList.remove(n);
                    var q = e === h;
                    if (e && e.classList.add(n),
                        e.hash && (m = c.getElementById(e.hash.replace("#", "")))) {
                        if (!m.classList.contains(i))
                            return void e.classList[q ? "remove" : "add"](n);
                        if (!q) {
                            var r = m.parentNode;
                            l = r.querySelectorAll("." + i + o);
                            for (var s = 0; s < l.length; s++) {
                                var t = l[s];
                                t.parentNode === r && t.classList.remove(n)
                            }
                            m.classList.add(n);
                            for (var u = [], v = r.querySelectorAll("." + i), s = 0; s < v.length; s++)
                                v[s].parentNode === r && u.push(v[s]);
                            a.trigger(m, a.eventName("shown", d), {
                                tabNumber: Array.prototype.indexOf.call(u, m)
                            }),
                            b.detail && b.detail.gesture.preventDefault()
                        }
                    }
                }
            })
    }(mui, window, document, "tab"),
    function(a, b, c) {
        var d = "mui-switch"
            , e = "mui-switch-handle"
            , f = "mui-active"
            , g = "mui-dragging"
            , h = "mui-disabled"
            , i = "." + e
            , j = function(a, b) {
            return b.classList && b.classList.contains(d) ? b : !1
        };
        a.registerTarget({
            name: c,
            index: 100,
            handle: j,
            target: !1
        });
        var k = function(a) {
            this.element = a,
                this.classList = this.element.classList,
                this.handle = this.element.querySelector(i),
                this.init(),
                this.initEvent()
        };
        k.prototype.init = function() {
            this.toggleWidth = this.element.offsetWidth,
                this.handleWidth = this.handle.offsetWidth,
                this.handleX = this.toggleWidth - this.handleWidth - 3
        }
            ,
            k.prototype.initEvent = function() {
                this.element.addEventListener(a.EVENT_START, this),
                    this.element.addEventListener("drag", this),
                    this.element.addEventListener("swiperight", this),
                    this.element.addEventListener(a.EVENT_END, this),
                    this.element.addEventListener(a.EVENT_CANCEL, this)
            }
            ,
            k.prototype.handleEvent = function(b) {
                if (!this.classList.contains(h))
                    switch (b.type) {
                        case a.EVENT_START:
                            this.start(b);
                            break;
                        case "drag":
                            this.drag(b);
                            break;
                        case "swiperight":
                            this.swiperight();
                            break;
                        case a.EVENT_END:
                        case a.EVENT_CANCEL:
                            this.end(b)
                    }
            }
            ,
            k.prototype.start = function(a) {
                this.handle.style.webkitTransitionDuration = this.element.style.webkitTransitionDuration = ".2s",
                    this.classList.add(g),
                (0 === this.toggleWidth || 0 === this.handleWidth) && this.init()
            }
            ,
            k.prototype.drag = function(a) {
                var b = a.detail;
                this.isDragging || ("left" === b.direction || "right" === b.direction) && (this.isDragging = !0,
                    this.lastChanged = void 0,
                    this.initialState = this.classList.contains(f)),
                this.isDragging && (this.setTranslateX(b.deltaX),
                    a.stopPropagation(),
                    b.gesture.preventDefault())
            }
            ,
            k.prototype.swiperight = function(a) {
                this.isDragging && a.stopPropagation()
            }
            ,
            k.prototype.end = function(b) {
                this.classList.remove(g),
                    this.isDragging ? (this.isDragging = !1,
                        b.stopPropagation(),
                        a.trigger(this.element, "toggle", {
                            isActive: this.classList.contains(f)
                        })) : this.toggle()
            }
            ,
            k.prototype.toggle = function(b) {
                var c = this.classList;
                b === !1 ? this.handle.style.webkitTransitionDuration = this.element.style.webkitTransitionDuration = "0s" : this.handle.style.webkitTransitionDuration = this.element.style.webkitTransitionDuration = ".2s",
                    c.contains(f) ? (c.remove(f),
                        this.handle.style.webkitTransform = "translate(0,0)") : (c.add(f),
                        this.handle.style.webkitTransform = "translate(" + this.handleX + "px,0)"),
                    a.trigger(this.element, "toggle", {
                        isActive: this.classList.contains(f)
                    })
            }
            ,
            k.prototype.setTranslateX = a.animationFrame(function(a) {
                if (this.isDragging) {
                    var b = !1;
                    (this.initialState && -a > this.handleX / 2 || !this.initialState && a > this.handleX / 2) && (b = !0),
                    this.lastChanged !== b && (b ? (this.handle.style.webkitTransform = "translate(" + (this.initialState ? 0 : this.handleX) + "px,0)",
                        this.classList[this.initialState ? "remove" : "add"](f)) : (this.handle.style.webkitTransform = "translate(" + (this.initialState ? this.handleX : 0) + "px,0)",
                        this.classList[this.initialState ? "add" : "remove"](f)),
                        this.lastChanged = b)
                }
            }),
            a.fn["switch"] = function(b) {
                var c = [];
                return this.each(function() {
                    var b = null
                        , d = this.getAttribute("data-switch");
                    d ? b = a.data[d] : (d = ++a.uuid,
                        a.data[d] = new k(this),
                        this.setAttribute("data-switch", d)),
                        c.push(b)
                }),
                    c.length > 1 ? c : c[0]
            }
            ,
            a.ready(function() {
                a("." + d)["switch"]()
            })
    }(mui, window, "toggle"),
    function(a, b, c) {
        function d(a, b) {
            var c = b ? "removeEventListener" : "addEventListener";
            a[c]("drag", F),
                a[c]("dragend", F),
                a[c]("swiperight", F),
                a[c]("swipeleft", F),
                a[c]("flick", F)
        }
        var e, f, g = "mui-active", h = "mui-selected", i = "mui-grid-view", j = "mui-table-view-radio", k = "mui-table-view-cell", l = "mui-collapse-content", m = "mui-disabled", n = "mui-switch", o = "mui-btn", p = "mui-slider-handle", q = "mui-slider-left", r = "mui-slider-right", s = "mui-transitioning", t = "." + p, u = "." + q, v = "." + r, w = "." + h, x = "." + o, y = .8, z = isOpened = openedActions = progress = !1, A = sliderActionLeft = sliderActionRight = buttonsLeft = buttonsRight = sliderDirection = sliderRequestAnimationFrame = !1, B = translateX = lastTranslateX = sliderActionLeftWidth = sliderActionRightWidth = 0, C = function(a) {
            a ? f ? f.classList.add(g) : e && e.classList.add(g) : (B && B.cancel(),
                f ? f.classList.remove(g) : e && e.classList.remove(g))
        }, D = function() {
            if (translateX !== lastTranslateX) {
                if (buttonsRight && buttonsRight.length > 0) {
                    progress = translateX / sliderActionRightWidth,
                    translateX < -sliderActionRightWidth && (translateX = -sliderActionRightWidth - Math.pow(-translateX - sliderActionRightWidth, y));
                    for (var a = 0, b = buttonsRight.length; b > a; a++) {
                        var c = buttonsRight[a];
                        "undefined" == typeof c._buttonOffset && (c._buttonOffset = c.offsetLeft),
                            buttonOffset = c._buttonOffset,
                            E(c, translateX - buttonOffset * (1 + Math.max(progress, -1)))
                    }
                }
                if (buttonsLeft && buttonsLeft.length > 0) {
                    progress = translateX / sliderActionLeftWidth,
                    translateX > sliderActionLeftWidth && (translateX = sliderActionLeftWidth + Math.pow(translateX - sliderActionLeftWidth, y));
                    for (var a = 0, b = buttonsLeft.length; b > a; a++) {
                        var d = buttonsLeft[a];
                        "undefined" == typeof d._buttonOffset && (d._buttonOffset = sliderActionLeftWidth - d.offsetLeft - d.offsetWidth),
                            buttonOffset = d._buttonOffset,
                        buttonsLeft.length > 1 && (d.style.zIndex = buttonsLeft.length - a),
                            E(d, translateX + buttonOffset * (1 - Math.min(progress, 1)))
                    }
                }
                E(A, translateX),
                    lastTranslateX = translateX
            }
            sliderRequestAnimationFrame = requestAnimationFrame(function() {
                D()
            })
        }, E = function(a, b) {
            a && (a.style.webkitTransform = "translate(" + b + "px,0)")
        };
        b.addEventListener(a.EVENT_START, function(b) {
            e && C(!1),
                e = f = !1,
                z = isOpened = openedActions = !1;
            for (var g = b.target, h = !1; g && g !== c; g = g.parentNode)
                if (g.classList) {
                    var p = g.classList;
                    if (("INPUT" === g.tagName && "radio" !== g.type && "checkbox" !== g.type || "BUTTON" === g.tagName || p.contains(n) || p.contains(o) || p.contains(m)) && (h = !0),
                            p.contains(l))
                        break;
                    if (p.contains(k)) {
                        e = g;
                        var q = e.parentNode.querySelector(w);
                        if (!e.parentNode.classList.contains(j) && q && q !== e)
                            return a.swipeoutClose(q),
                                void (e = h = !1);
                        if (!e.parentNode.classList.contains(i)) {
                            var r = e.querySelector("a");
                            r && r.parentNode === e && (f = r)
                        }
                        var s = e.querySelector(t);
                        s && (d(e),
                            b.stopPropagation()),
                        h || (s ? (B && B.cancel(),
                            B = a.later(function() {
                                C(!0)
                            }, 100)) : C(!0));
                        break
                    }
                }
        }),
            b.addEventListener(a.EVENT_MOVE, function(a) {
                C(!1)
            });
        var F = {
            handleEvent: function(a) {
                switch (a.type) {
                    case "drag":
                        this.drag(a);
                        break;
                    case "dragend":
                        this.dragend(a);
                        break;
                    case "flick":
                        this.flick(a);
                        break;
                    case "swiperight":
                        this.swiperight(a);
                        break;
                    case "swipeleft":
                        this.swipeleft(a)
                }
            },
            drag: function(a) {
                if (e) {
                    z || (A = sliderActionLeft = sliderActionRight = buttonsLeft = buttonsRight = sliderDirection = sliderRequestAnimationFrame = !1,
                        A = e.querySelector(t),
                    A && (sliderActionLeft = e.querySelector(u),
                        sliderActionRight = e.querySelector(v),
                    sliderActionLeft && (sliderActionLeftWidth = sliderActionLeft.offsetWidth,
                        buttonsLeft = sliderActionLeft.querySelectorAll(x)),
                    sliderActionRight && (sliderActionRightWidth = sliderActionRight.offsetWidth,
                        buttonsRight = sliderActionRight.querySelectorAll(x)),
                        e.classList.remove(s),
                        isOpened = e.classList.contains(h),
                    isOpened && (openedActions = e.querySelector(u + w) ? "left" : "right")));
                    var b = a.detail
                        , c = b.direction
                        , d = b.angle;
                    if ("left" === c && (d > 150 || -150 > d) ? (buttonsRight || buttonsLeft && isOpened) && (z = !0) : "right" === c && d > -30 && 30 > d && (buttonsLeft || buttonsRight && isOpened) && (z = !0),
                            z) {
                        a.stopPropagation(),
                            a.detail.gesture.preventDefault();
                        var f = a.detail.deltaX;
                        if (isOpened && ("right" === openedActions ? f -= sliderActionRightWidth : f += sliderActionLeftWidth),
                            f > 0 && !buttonsLeft || 0 > f && !buttonsRight) {
                            if (!isOpened)
                                return;
                            f = 0
                        }
                        0 > f ? sliderDirection = "toLeft" : f > 0 ? sliderDirection = "toRight" : sliderDirection || (sliderDirection = "toLeft"),
                        sliderRequestAnimationFrame || D(),
                            translateX = f
                    }
                }
            },
            flick: function(a) {
                z && a.stopPropagation()
            },
            swipeleft: function(a) {
                z && a.stopPropagation()
            },
            swiperight: function(a) {
                z && a.stopPropagation()
            },
            dragend: function(b) {
                if (z) {
                    b.stopPropagation(),
                    sliderRequestAnimationFrame && (cancelAnimationFrame(sliderRequestAnimationFrame),
                        sliderRequestAnimationFrame = null);
                    var c = b.detail;
                    z = !1;
                    var d = "close"
                        , f = "toLeft" === sliderDirection ? sliderActionRightWidth : sliderActionLeftWidth
                        , g = c.swipe || Math.abs(translateX) > f / 2;
                    g && (isOpened ? "left" === c.direction && "right" === openedActions ? d = "open" : "right" === c.direction && "left" === openedActions && (d = "open") : d = "open"),
                        e.classList.add(s);
                    var i;
                    if ("open" === d) {
                        var j = "toLeft" === sliderDirection ? -f : f;
                        if (E(A, j),
                                i = "toLeft" === sliderDirection ? buttonsRight : buttonsLeft,
                            "undefined" != typeof i) {
                            for (var k = null, l = 0; l < i.length; l++)
                                k = i[l],
                                    E(k, j);
                            k.parentNode.classList.add(h),
                                e.classList.add(h),
                            isOpened || a.trigger(e, "toLeft" === sliderDirection ? "slideleft" : "slideright")
                        }
                    } else
                        E(A, 0),
                        sliderActionLeft && sliderActionLeft.classList.remove(h),
                        sliderActionRight && sliderActionRight.classList.remove(h),
                            e.classList.remove(h);
                    var m;
                    if (buttonsLeft && buttonsLeft.length > 0 && buttonsLeft !== i)
                        for (var l = 0, n = buttonsLeft.length; n > l; l++) {
                            var o = buttonsLeft[l];
                            m = o._buttonOffset,
                            "undefined" == typeof m && (o._buttonOffset = sliderActionLeftWidth - o.offsetLeft - o.offsetWidth),
                                E(o, m)
                        }
                    if (buttonsRight && buttonsRight.length > 0 && buttonsRight !== i)
                        for (var l = 0, n = buttonsRight.length; n > l; l++) {
                            var p = buttonsRight[l];
                            m = p._buttonOffset,
                            "undefined" == typeof m && (p._buttonOffset = p.offsetLeft),
                                E(p, -m)
                        }
                }
            }
        };
        a.swipeoutOpen = function(b, c) {
            if (b) {
                var d = b.classList;
                if (!d.contains(h)) {
                    c || (c = b.querySelector(v) ? "right" : "left");
                    var e = b.querySelector(a.classSelector(".slider-" + c));
                    if (e) {
                        e.classList.add(h),
                            d.add(h),
                            d.remove(s);
                        for (var f, g = e.querySelectorAll(x), i = e.offsetWidth, j = "right" === c ? -i : i, k = g.length, l = 0; k > l; l++)
                            f = g[l],
                                "right" === c ? E(f, -f.offsetLeft) : E(f, i - f.offsetWidth - f.offsetLeft);
                        d.add(s);
                        for (var l = 0; k > l; l++)
                            E(g[l], j);
                        E(b.querySelector(t), j)
                    }
                }
            }
        }
            ,
            a.swipeoutClose = function(b) {
                if (b) {
                    var c = b.classList;
                    if (c.contains(h)) {
                        var d = b.querySelector(v + w) ? "right" : "left"
                            , e = b.querySelector(a.classSelector(".slider-" + d));
                        if (e) {
                            e.classList.remove(h),
                                c.remove(h),
                                c.add(s);
                            var f, g = e.querySelectorAll(x), i = e.offsetWidth, j = g.length;
                            E(b.querySelector(t), 0);
                            for (var k = 0; j > k; k++)
                                f = g[k],
                                    "right" === d ? E(f, -f.offsetLeft) : E(f, i - f.offsetWidth - f.offsetLeft)
                        }
                    }
                }
            }
            ,
            b.addEventListener(a.EVENT_END, function(a) {
                e && (C(!1),
                A && d(e, !0))
            }),
            b.addEventListener(a.EVENT_CANCEL, function(a) {
                e && (C(!1),
                A && d(e, !0))
            });
        var G = function(b) {
            var c = b.target && b.target.type || "";
            if ("radio" !== c && "checkbox" !== c) {
                var d = e.classList;
                if (d.contains("mui-radio")) {
                    var f = e.querySelector("input[type=radio]");
                    f && (f.disabled || f.readOnly || (f.checked = !f.checked,
                        a.trigger(f, "change")))
                } else if (d.contains("mui-checkbox")) {
                    var f = e.querySelector("input[type=checkbox]");
                    f && (f.disabled || f.readOnly || (f.checked = !f.checked,
                        a.trigger(f, "change")))
                }
            }
        };
        b.addEventListener(a.EVENT_CLICK, function(a) {
            e && e.classList.contains("mui-collapse") && a.preventDefault()
        }),
            b.addEventListener("doubletap", function(a) {
                e && G(a)
            });
        var H = /^(INPUT|TEXTAREA|BUTTON|SELECT)$/;
        b.addEventListener("tap", function(b) {
            if (e) {
                var c = !1
                    , d = e.classList
                    , f = e.parentNode;
                if (f && f.classList.contains(j)) {
                    if (d.contains(h))
                        return;
                    var i = f.querySelector("li" + w);
                    return i && i.classList.remove(h),
                        d.add(h),
                        void a.trigger(e, "selected", {
                            el: e
                        })
                }
                if (d.contains("mui-collapse") && !e.parentNode.classList.contains("mui-unfold")) {
                    if (H.test(b.target.tagName) || b.detail.gesture.preventDefault(),
                            !d.contains(g)) {
                        var k = e.parentNode.querySelector(".mui-collapse.mui-active");
                        k && k.classList.remove(g),
                            c = !0
                    }
                    d.toggle(g),
                    c && a.trigger(e, "expand")
                } else
                    G(b)
            }
        })
    }(mui, window, document),
    function(a, b) {
        a.alert = function(c, d, e, f) {
            if (a.os.plus) {
                if ("undefined" == typeof c)
                    return;
                "function" == typeof d ? (f = d,
                    d = null,
                    e = "确定") : "function" == typeof e && (f = e,
                        e = null),
                    a.plusReady(function() {
                        plus.nativeUI.alert(c, f, d, e)
                    })
            } else
                b.alert(c)
        }
    }(mui, window),
    function(a, b) {
        a.confirm = function(c, d, e, f) {
            if (a.os.plus) {
                if ("undefined" == typeof c)
                    return;
                "function" == typeof d ? (f = d,
                    d = null,
                    e = null) : "function" == typeof e && (f = e,
                        e = null),
                    a.plusReady(function() {
                        plus.nativeUI.confirm(c, f, d, e)
                    })
            } else
                f(b.confirm(c) ? {
                    index: 0
                } : {
                    index: 1
                })
        }
    }(mui, window),
    function(a, b) {
        a.prompt = function(c, d, e, f, g) {
            if (a.os.plus) {
                if ("undefined" == typeof message)
                    return;
                "function" == typeof d ? (g = d,
                    d = null,
                    e = null,
                    f = null) : "function" == typeof e ? (g = e,
                    e = null,
                    f = null) : "function" == typeof f && (g = f,
                        f = null),
                    a.plusReady(function() {
                        plus.nativeUI.prompt(c, g, e, d, f)
                    })
            } else {
                var h = b.prompt(c);
                g(h ? {
                    index: 0,
                    value: h
                } : {
                    index: 1,
                    value: ""
                })
            }
        }
    }(mui, window),
    function(a, b) {
        var c = "mui-active";
        a.toast = function(b, d) {
            var e = {
                "long": 3500,
                "short": 2e3
            };
            if (d = a.extend({
                    duration: "short"
                }, d || {}),
                !a.os.plus || "div" === d.type) {
                "number" == typeof d.duration ? duration = d.duration > 0 ? d.duration : e["short"] : duration = e[d.duration],
                duration || (duration = e["short"]);
                var f = document.createElement("div");
                return f.classList.add("mui-toast-container"),
                    f.innerHTML = '<div class="mui-toast-message">' + b + "</div>",
                    f.addEventListener("webkitTransitionEnd", function() {
                        f.classList.contains(c) || (f.parentNode.removeChild(f),
                            f = null)
                    }),
                    f.addEventListener("click", function() {
                        f.parentNode.removeChild(f),
                            f = null
                    }),
                    document.body.appendChild(f),
                    f.offsetHeight,
                    f.classList.add(c),
                    setTimeout(function() {
                        f && f.classList.remove(c)
                    }, duration),
                    {
                        isVisible: function() {
                            return !!f
                        }
                    }
            }
            a.plusReady(function() {
                plus.nativeUI.toast(b, {
                    verticalAlign: "bottom",
                    duration: d.duration
                })
            })
        }
    }(mui, window),
    function(a, b, c) {
        var d = "mui-popup"
            , e = "mui-popup-backdrop"
            , f = "mui-popup-in"
            , g = "mui-popup-out"
            , h = "mui-popup-inner"
            , i = "mui-popup-title"
            , j = "mui-popup-text"
            , k = "mui-popup-input"
            , l = "mui-popup-buttons"
            , m = "mui-popup-button"
            , n = "mui-popup-button-bold"
            , e = "mui-popup-backdrop"
            , o = "mui-active"
            , p = []
            , q = function() {
            var b = c.createElement("div");
            return b.classList.add(e),
                b.addEventListener(a.EVENT_MOVE, a.preventDefault),
                b.addEventListener("webkitTransitionEnd", function() {
                    this.classList.contains(o) || b.parentNode && b.parentNode.removeChild(b)
                }),
                b
        }()
            , r = function(a) {
            return '<div class="' + k + '"><input type="text" autofocus placeholder="' + (a || "") + '"/></div>'
        }
            , s = function(a, b, c) {
            return '<div class="' + h + '"><div class="' + i + '">' + b + '</div><div class="' + j + '">' + a.replace(/\r\n/g, "<br/>").replace(/\n/g, "<br/>") + "</div>" + (c || "") + "</div>"
        }
            , t = function(a) {
            for (var b = a.length, c = [], d = 0; b > d; d++)
                c.push('<span class="' + m + (d === b - 1 ? " " + n : "") + '">' + a[d] + "</span>");
            return '<div class="' + l + '">' + c.join("") + "</div>"
        }
            , u = function(b, e) {
            var h = c.createElement("div");
            h.className = d,
                h.innerHTML = b;
            var i = function() {
                h.parentNode && h.parentNode.removeChild(h),
                    h = null
            };
            h.addEventListener(a.EVENT_MOVE, a.preventDefault),
                h.addEventListener("webkitTransitionEnd", function(a) {
                    h && a.target === h && h.classList.contains(g) && i()
                }),
                h.style.display = "block",
                c.body.appendChild(h),
                h.offsetHeight,
                h.classList.add(f),
            q.classList.contains(o) || (q.style.display = "block",
                c.body.appendChild(q),
                q.offsetHeight,
                q.classList.add(o));
            var j = a.qsa("." + m, h)
                , l = h.querySelector("." + k + " input")
                , n = {
                element: h,
                close: function(a, b) {
                    if (h) {
                        var c = e && e({
                                index: a || 0,
                                value: l && l.value || ""
                            });
                        if (c === !1)
                            return;
                        b !== !1 ? (h.classList.remove(f),
                            h.classList.add(g)) : i(),
                            p.pop(),
                            p.length ? p[p.length - 1].show(b) : q.classList.remove(o)
                    }
                }
            }
                , r = function(a) {
                n.close(j.indexOf(a.target))
            };
            return a(h).on("tap", "." + m, r),
            p.length && p[p.length - 1].hide(),
                p.push({
                    close: n.close,
                    show: function(a) {
                        h.style.display = "block",
                            h.offsetHeight,
                            h.classList.add(f)
                    },
                    hide: function() {
                        h.style.display = "none",
                            h.classList.remove(f)
                    }
                }),
                n
        }
            , v = function(b, c, d, e, f) {
            return "undefined" != typeof b ? ("function" == typeof c ? (e = c,
                f = d,
                c = null,
                d = null) : "function" == typeof d && (f = e,
                    e = d,
                    d = null),
                a.os.plus && "div" !== f ? plus.nativeUI.alert(b, e, c || "提示", d || "确定") : u(s(b, c || "提示") + t([d || "确定"]), e)) : void 0
        }
            , w = function(b, c, d, e, f) {
            return "undefined" != typeof b ? ("function" == typeof c ? (e = c,
                f = d,
                c = null,
                d = null) : "function" == typeof d && (f = e,
                    e = d,
                    d = null),
                a.os.plus && "div" !== f ? plus.nativeUI.confirm(b, e, c, d || ["取消", "确认"]) : u(s(b, c || "提示") + t(d || ["取消", "确认"]), e)) : void 0
        }
            , x = function(b, c, d, e, f, g) {
            return "undefined" != typeof b ? ("function" == typeof c ? (f = c,
                g = d,
                c = null,
                d = null,
                e = null) : "function" == typeof d ? (f = d,
                g = e,
                d = null,
                e = null) : "function" == typeof e && (g = f,
                    f = e,
                    e = null),
                a.os.plus && "div" !== g ? plus.nativeUI.prompt(b, f, d || "提示", c, e || ["取消", "确认"]) : u(s(b, d || "提示", r(c)) + t(e || ["取消", "确认"]), f)) : void 0
        }
            , y = function() {
            return p.length ? (p[p.length - 1].close(),
                !0) : !1
        }
            , z = function() {
            for (; p.length; )
                p[p.length - 1].close()
        };
        a.closePopup = y,
            a.closePopups = z,
            a.alert = v,
            a.confirm = w,
            a.prompt = x
    }(mui, window, document),
    function(a, b) {
        var c = "mui-progressbar"
            , d = "mui-progressbar-in"
            , e = "mui-progressbar-out"
            , f = "mui-progressbar-infinite"
            , g = ".mui-progressbar"
            , h = function(b) {
            if (b = a(b || "body"),
                0 !== b.length) {
                if (b = b[0],
                        b.classList.contains(c))
                    return b;
                var d = b.querySelectorAll(g);
                if (d)
                    for (var e = 0, f = d.length; f > e; e++) {
                        var h = d[e];
                        if (h.parentNode === b)
                            return h
                    }
            }
        }
            , i = function(h, i, j) {
            if ("number" == typeof h && (j = i,
                    i = h,
                    h = "body"),
                    h = a(h || "body"),
                0 !== h.length) {
                h = h[0];
                var l;
                if (h.classList.contains(c))
                    l = h;
                else {
                    var m = h.querySelectorAll(g + ":not(." + e + ")");
                    if (m)
                        for (var n = 0, o = m.length; o > n; n++) {
                            var p = m[n];
                            if (p.parentNode === h) {
                                l = p;
                                break
                            }
                        }
                    l ? l.classList.add(d) : (l = b.createElement("span"),
                        l.className = c + " " + d + ("undefined" != typeof i ? "" : " " + f) + (j ? " " + c + "-" + j : ""),
                    "undefined" != typeof i && (l.innerHTML = "<span></span>"),
                        h.appendChild(l))
                }
                return i && k(h, i),
                    l
            }
        }
            , j = function(a) {
            var b = h(a);
            if (b) {
                var c = b.classList;
                c.contains(d) && !c.contains(e) && (c.remove(d),
                    c.add(e),
                    b.addEventListener("webkitAnimationEnd", function() {
                        b.parentNode && b.parentNode.removeChild(b),
                            b = null
                    }))
            }
        }
            , k = function(a, b, c) {
            "number" == typeof a && (c = b,
                b = a,
                a = !1);
            var d = h(a);
            if (d && !d.classList.contains(f)) {
                b && (b = Math.min(Math.max(b, 0), 100)),
                    d.offsetHeight;
                var e = d.querySelector("span");
                if (e) {
                    var g = e.style;
                    g.webkitTransform = "translate3d(" + (-100 + b) + "%,0,0)",
                        "undefined" != typeof c ? g.webkitTransitionDuration = c + "ms" : g.webkitTransitionDuration = ""
                }
                return d
            }
        };
        a.fn.progressbar = function(a) {
            var b = [];
            return a = a || {},
                this.each(function() {
                    var c = this
                        , d = c.mui_plugin_progressbar;
                    d ? a && d.setOptions(a) : c.mui_plugin_progressbar = d = {
                        options: a,
                        setOptions: function(a) {
                            this.options = a
                        },
                        show: function() {
                            return i(c, this.options.progress, this.options.color)
                        },
                        setProgress: function(a) {
                            return k(c, a)
                        },
                        hide: function() {
                            return j(c)
                        }
                    },
                        b.push(d)
                }),
                1 === b.length ? b[0] : b
        }
    }(mui, document),
    function(a, b, c) {
        var d = "mui-icon"
            , e = "mui-icon-clear"
            , f = "mui-icon-speech"
            , g = "mui-icon-search"
            , h = "mui-icon-eye"
            , i = "mui-input-row"
            , j = "mui-placeholder"
            , k = "mui-tooltip"
            , l = "mui-hidden"
            , m = "mui-focusin"
            , n = "." + e
            , o = "." + f
            , p = "." + h
            , q = "." + j
            , r = "." + k
            , s = function(a) {
            for (; a && a !== c; a = a.parentNode)
                if (a.classList && a.classList.contains(i))
                    return a;
            return null
        }
            , t = function(a, b) {
            this.element = a,
                this.options = b || {
                        actions: "clear"
                    },
                ~this.options.actions.indexOf("slider") ? (this.sliderActionClass = k + " " + l,
                    this.sliderActionSelector = r) : (~this.options.actions.indexOf("clear") && (this.clearActionClass = d + " " + e + " " + l,
                    this.clearActionSelector = n),
                ~this.options.actions.indexOf("speech") && (this.speechActionClass = d + " " + f,
                    this.speechActionSelector = o),
                ~this.options.actions.indexOf("search") && (this.searchActionClass = j,
                    this.searchActionSelector = q),
                ~this.options.actions.indexOf("password") && (this.passwordActionClass = d + " " + h,
                    this.passwordActionSelector = p)),
                this.init()
        };
        t.prototype.init = function() {
            this.initAction(),
                this.initElementEvent()
        }
            ,
            t.prototype.initAction = function() {
                var b = this
                    , c = b.element.parentNode;
                c && (b.sliderActionClass ? b.sliderAction = b.createAction(c, b.sliderActionClass, b.sliderActionSelector) : (b.searchActionClass && (b.searchAction = b.createAction(c, b.searchActionClass, b.searchActionSelector),
                    b.searchAction.addEventListener("tap", function(c) {
                        a.focus(b.element),
                            c.stopPropagation()
                    })),
                b.speechActionClass && (b.speechAction = b.createAction(c, b.speechActionClass, b.speechActionSelector),
                    b.speechAction.addEventListener("click", a.stopPropagation),
                    b.speechAction.addEventListener("tap", function(a) {
                        b.speechActionClick(a)
                    })),
                b.clearActionClass && (b.clearAction = b.createAction(c, b.clearActionClass, b.clearActionSelector),
                    b.clearAction.addEventListener("tap", function(a) {
                        b.clearActionClick(a)
                    })),
                b.passwordActionClass && (b.passwordAction = b.createAction(c, b.passwordActionClass, b.passwordActionSelector),
                    b.passwordAction.addEventListener("tap", function(a) {
                        b.passwordActionClick(a)
                    }))))
            }
            ,
            t.prototype.createAction = function(a, b, e) {
                var f = a.querySelector(e);
                if (!f) {
                    var f = c.createElement("span");
                    f.className = b,
                    b === this.searchActionClass && (f.innerHTML = '<span class="' + d + " " + g + '"></span><span>' + this.element.getAttribute("placeholder") + "</span>",
                        this.element.setAttribute("placeholder", ""),
                    this.element.value.trim() && a.classList.add("mui-active")),
                        a.insertBefore(f, this.element.nextSibling)
                }
                return f
            }
            ,
            t.prototype.initElementEvent = function() {
                var b = this.element;
                if (this.sliderActionClass) {
                    var c = this.sliderAction
                        , d = null
                        , e = function() {
                        c.classList.remove(l);
                        var a = b.offsetLeft
                            , e = b.offsetWidth - 28
                            , f = c.offsetWidth
                            , g = Math.abs(b.max - b.min)
                            , h = e / g * Math.abs(b.value - b.min);
                        c.style.left = 14 + a + h - f / 2 + "px",
                            c.innerText = b.value,
                        d && clearTimeout(d),
                            d = setTimeout(function() {
                                c.classList.add(l)
                            }, 1e3)
                    };
                    b.addEventListener("input", e),
                        b.addEventListener("tap", e),
                        b.addEventListener(a.EVENT_MOVE, function(a) {
                            a.stopPropagation()
                        })
                } else {
                    if (this.clearActionClass) {
                        var f = this.clearAction;
                        if (!f)
                            return;
                        a.each(["keyup", "change", "input", "focus", "cut", "paste"], function(a, c) {
                            !function(a) {
                                b.addEventListener(a, function() {
                                    f.classList[b.value.trim() ? "remove" : "add"](l)
                                })
                            }(c)
                        }),
                            b.addEventListener("blur", function() {
                                f.classList.add(l)
                            })
                    }
                    this.searchActionClass && (b.addEventListener("focus", function() {
                        b.parentNode.classList.add("mui-active")
                    }),
                        b.addEventListener("blur", function() {
                            b.value.trim() || b.parentNode.classList.remove("mui-active")
                        }))
                }
            }
            ,
            t.prototype.setPlaceholder = function(a) {
                if (this.searchActionClass) {
                    var b = this.element.parentNode.querySelector(q);
                    b && (b.getElementsByTagName("span")[1].innerText = a)
                } else
                    this.element.setAttribute("placeholder", a)
            }
            ,
            t.prototype.passwordActionClick = function(a) {
                "text" === this.element.type ? this.element.type = "password" : this.element.type = "text",
                    this.passwordAction.classList.toggle("mui-active"),
                    a.preventDefault()
            }
            ,
            t.prototype.clearActionClick = function(b) {
                var c = this;
                c.element.value = "",
                    a.focus(c.element),
                    c.clearAction.classList.add(l),
                    b.preventDefault()
            }
            ,
            t.prototype.speechActionClick = function(d) {
                if (b.plus) {
                    var e = this
                        , f = e.element.value;
                    e.element.value = "",
                        c.body.classList.add(m),
                        plus.speech.startRecognize({
                            engine: "iFly"
                        }, function(b) {
                            e.element.value += b,
                                a.focus(e.element),
                                plus.speech.stopRecognize(),
                                a.trigger(e.element, "recognized", {
                                    value: e.element.value
                                }),
                            f !== e.element.value && (a.trigger(e.element, "change"),
                                a.trigger(e.element, "input"))
                        }, function(a) {
                            c.body.classList.remove(m)
                        })
                } else
                    alert("only for 5+");
                d.preventDefault()
            }
            ,
            a.fn.input = function(b) {
                var c = [];
                return this.each(function() {
                    var b = null
                        , d = []
                        , e = s(this.parentNode);
                    if ("range" === this.type && e.classList.contains("mui-input-range"))
                        d.push("slider");
                    else {
                        var f = this.classList;
                        f.contains("mui-input-clear") && d.push("clear"),
                        a.os.android && a.os.stream || !f.contains("mui-input-speech") || d.push("speech"),
                        f.contains("mui-input-password") && d.push("password"),
                        "search" === this.type && e.classList.contains("mui-search") && d.push("search")
                    }
                    var g = this.getAttribute("data-input-" + d[0]);
                    if (g)
                        b = a.data[g];
                    else {
                        g = ++a.uuid,
                            b = a.data[g] = new t(this,{
                                actions: d.join(",")
                            });
                        for (var h = 0, i = d.length; i > h; h++)
                            this.setAttribute("data-input-" + d[h], g)
                    }
                    c.push(b)
                }),
                    1 === c.length ? c[0] : c
            }
            ,
            a.ready(function() {
                a(".mui-input-row input").input()
            })
    }(mui, window, document),
    function(a, b) {
        var c = "mui-active"
            , d = /^rgba\((\d{1,3}),\s*(\d{1,3}),\s*(\d{1,3}),\s*(\d*(?:\.\d+)?)\)$/
            , e = function(a) {
            var b = a.match(d);
            return b && 5 === b.length ? [b[1], b[2], b[3], b[4]] : []
        }
            , f = function(c, d) {
            if (this.element = c,
                    this.options = a.extend({
                        top: 0,
                        offset: 150,
                        duration: 16,
                        scrollby: b
                    }, d || {}),
                    this.scrollByElem = this.options.scrollby || b,
                    !this.scrollByElem)
                throw new Error("监听滚动的元素不存在");
            this.isNativeScroll = !1,
                this.scrollByElem === b ? this.isNativeScroll = !0 : ~this.scrollByElem.className.indexOf("mui-scroll-wrapper") || (this.isNativeScroll = !0),
                this._style = this.element.style,
                this._bgColor = this._style.backgroundColor;
            var f = e(mui.getStyles(this.element, "backgroundColor"));
            if (!f.length)
                throw new Error("元素背景颜色必须为RGBA");
            this._R = f[0],
                this._G = f[1],
                this._B = f[2],
                this._A = parseFloat(f[3]),
                this.lastOpacity = this._A,
                this._bufferFn = a.buffer(this.handleScroll, this.options.duration, this),
                this.initEvent()
        };
        f.prototype.initEvent = function() {
            this.scrollByElem.addEventListener("scroll", this._bufferFn),
            this.isNativeScroll && this.scrollByElem.addEventListener(a.EVENT_MOVE, this._bufferFn)
        }
            ,
            f.prototype.handleScroll = function(d) {
                var e = b.scrollY;
                !this.isNativeScroll && d && d.detail && (e = -d.detail.y);
                var f = (e - this.options.top) / this.options.offset + this._A;
                f = Math.min(Math.max(this._A, f), 1),
                    this._style.backgroundColor = "rgba(" + this._R + "," + this._G + "," + this._B + "," + f + ")",
                    f > this._A ? this.element.classList.add(c) : this.element.classList.remove(c),
                this.lastOpacity !== f && (a.trigger(this.element, "alpha", {
                    alpha: f
                }),
                    this.lastOpacity = f)
            }
            ,
            f.prototype.destory = function() {
                this.scrollByElem.removeEventListener("scroll", this._bufferFn),
                    this.scrollByElem.removeEventListener(a.EVENT_MOVE, this._bufferFn),
                    this.element.style.backgroundColor = this._bgColor,
                    this.element.mui_plugin_transparent = null
            }
            ,
            a.fn.transparent = function(a) {
                a = a || {};
                var c = [];
                return this.each(function() {
                    var d = this.mui_plugin_transparent;
                    if (!d) {
                        var e = this.getAttribute("data-top")
                            , g = this.getAttribute("data-offset")
                            , h = this.getAttribute("data-duration")
                            , i = this.getAttribute("data-scrollby");
                        null !== e && "undefined" == typeof a.top && (a.top = e),
                        null !== g && "undefined" == typeof a.offset && (a.offset = g),
                        null !== h && "undefined" == typeof a.duration && (a.duration = h),
                        null !== i && "undefined" == typeof a.scrollby && (a.scrollby = document.querySelector(i) || b),
                            d = this.mui_plugin_transparent = new f(this,a)
                    }
                    c.push(d)
                }),
                    1 === c.length ? c[0] : c
            }
            ,
            a.ready(function() {
                a(".mui-bar-transparent").transparent()
            })
    }(mui, window),
    function(a) {
        var b = "ontouchstart"in document
            , c = b ? "tap" : "click"
            , d = "change"
            , e = "mui-numbox"
            , f = ".mui-btn-numbox-plus,.mui-numbox-btn-plus"
            , g = ".mui-btn-numbox-minus,.mui-numbox-btn-minus"
            , h = ".mui-input-numbox,.mui-numbox-input"
            , i = a.Numbox = a.Class.extend({
            init: function(b, c) {
                var d = this;
                if (!b)
                    throw "构造 numbox 时缺少容器元素";
                d.holder = b,
                    c = c || {},
                    c.step = parseInt(c.step || 1),
                    d.options = c,
                    d.input = a.qsa(h, d.holder)[0],
                    d.plus = a.qsa(f, d.holder)[0],
                    d.minus = a.qsa(g, d.holder)[0],
                    d.checkValue(),
                    d.initEvent()
            },
            initEvent: function() {
                var b = this;
                b.plus.addEventListener(c, function(c) {
                    var e = parseInt(b.input.value) + b.options.step;
                    b.input.value = e.toString(),
                        a.trigger(b.input, d, null)
                }),
                    b.minus.addEventListener(c, function(c) {
                        var e = parseInt(b.input.value) - b.options.step;
                        b.input.value = e.toString(),
                            a.trigger(b.input, d, null)
                    }),
                    b.input.addEventListener(d, function(c) {
                        b.checkValue();
                        var e = parseInt(b.input.value);
                        a.trigger(b.holder, d, {
                            value: e
                        })
                    })
            },
            getValue: function() {
                var a = this;
                return parseInt(a.input.value)
            },
            checkValue: function() {
                var a = this
                    , b = a.input.value;
                if (null == b || "" == b || isNaN(b))
                    a.input.value = a.options.min || 0,
                        a.minus.disabled = null != a.options.min;
                else {
                    var b = parseInt(b);
                    null != a.options.max && !isNaN(a.options.max) && b >= parseInt(a.options.max) ? (b = a.options.max,
                        a.plus.disabled = !0) : a.plus.disabled = !1,
                        null != a.options.min && !isNaN(a.options.min) && b <= parseInt(a.options.min) ? (b = a.options.min,
                            a.minus.disabled = !0) : a.minus.disabled = !1,
                        a.input.value = b
                }
            },
            setOption: function(a, b) {
                var c = this;
                c.options[a] = b
            },
            setValue: function(a) {
                this.input.value = a,
                    this.checkValue()
            }
        });
        a.fn.numbox = function(a) {
            return this.each(function(a, b) {
                if (!b.numbox)
                    if (d)
                        b.numbox = new i(b,d);
                    else {
                        var c = b.getAttribute("data-numbox-options")
                            , d = c ? JSON.parse(c) : {};
                        d.step = b.getAttribute("data-numbox-step") || d.step,
                            d.min = b.getAttribute("data-numbox-min") || d.min,
                            d.max = b.getAttribute("data-numbox-max") || d.max,
                            b.numbox = new i(b,d)
                    }
            }),
                this[0] ? this[0].numbox : null
        }
            ,
            a.ready(function() {
                a("." + e).numbox()
            })
    }(mui),
    function(a, b, c) {
        var d = "mui-disabled"
            , e = "reset"
            , f = "loading"
            , g = {
            loadingText: "Loading...",
            loadingIcon: "mui-spinner mui-spinner-white",
            loadingIconPosition: "left"
        }
            , h = function(b, c) {
            this.element = b,
                this.options = a.extend({}, g, c),
            this.options.loadingText || (this.options.loadingText = g.loadingText),
            null === this.options.loadingIcon && (this.options.loadingIcon = "mui-spinner",
            "rgb(255, 255, 255)" === a.getStyles(this.element, "color") && (this.options.loadingIcon += " mui-spinner-white")),
                this.isInput = "INPUT" === this.element.tagName,
                this.resetHTML = this.isInput ? this.element.value : this.element.innerHTML,
                this.state = ""
        };
        h.prototype.loading = function() {
            this.setState(f)
        }
            ,
            h.prototype.reset = function() {
                this.setState(e)
            }
            ,
            h.prototype.setState = function(a) {
                if (this.state === a)
                    return !1;
                if (this.state = a,
                    a === e)
                    this.element.disabled = !1,
                        this.element.classList.remove(d),
                        this.setHtml(this.resetHTML);
                else if (a === f) {
                    this.element.disabled = !0,
                        this.element.classList.add(d);
                    var b = this.isInput ? this.options.loadingText : "<span>" + this.options.loadingText + "</span>";
                    this.options.loadingIcon && !this.isInput && ("right" === this.options.loadingIconPosition ? b += '&nbsp;<span class="' + this.options.loadingIcon + '"></span>' : b = '<span class="' + this.options.loadingIcon + '"></span>&nbsp;' + b),
                        this.setHtml(b)
                }
            }
            ,
            h.prototype.setHtml = function(a) {
                this.isInput ? this.element.value = a : this.element.innerHTML = a
            }
            ,
            a.fn.button = function(a) {
                var b = [];
                return this.each(function() {
                    var c = this.mui_plugin_button;
                    if (!c) {
                        var d = this.getAttribute("data-loading-text")
                            , g = this.getAttribute("data-loading-icon")
                            , i = this.getAttribute("data-loading-icon-position");
                        this.mui_plugin_button = c = new h(this,{
                            loadingText: d,
                            loadingIcon: g,
                            loadingIconPosition: i
                        })
                    }
                    (a === f || a === e) && c.setState(a),
                        b.push(c)
                }),
                    1 === b.length ? b[0] : b
            }
    }(mui, window, document);
