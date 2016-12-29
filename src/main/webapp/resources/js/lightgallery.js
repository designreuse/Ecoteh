var lightgallery = function (aQ, aP) {
    function aO() {
        if (at.showOverlay) {
            var b = aF();
            aG(ab, {
                width: b[0],
                height: b[1]
            }), "block" != ab.style.display && (aG(ab, {display: "block"}), V(ab, {opacity: at.overlayOpacity}, 300))
        }
    }

    function aN() {
        V(ab, {opacity: 0}, 300, function () {
            ab.style.display = "none", ae = 0
        })
    }

    function aM(y, x) {
        var w, v, r, q;
        r = 2 * at.minPadding;
        var m, l = ag.width, k = ag.height, f = aF(), c = f[2], f = f[3];
        w = c - r;
        var B = f - r - aA;
        if (0 === y || x && !y) {
            if (l > w || k > B) {
                r = w;
                var A = w * k / l;
                A > B && (A = B, r = B * l / k), r = an.width = r, q = (an.height = A) + aA
            } else {
                r = an.width = l, q = (an.height = k) + aA
            }
        } else {
            1 == y ? (r = an.width = l, q = (an.height = k) + aA) : 1 > y || 1 < y ? (r = an.width *= y, q = (an.height *= y) + aA) : (r = q = 300, m = !0)
        }
        (w = r > w || q > B + aA) ? (v = ar.fitScreen, fsClass = "LG_fitScreen") : an.width == l && an.height == k || (v = ar.fullSize, fsClass = "LG_zoomNormal"), ak.$disabled = !1, an.getAttribute("width") == l ? w ? (ak.id = fsClass, ak.setAttribute("title", v)) : (ak.id = "LG_zoom_disabled", ak.$disabled = !0) : (ak.id = "LG_zoomNormal", ak.setAttribute("title", ar.fullSize)), r = Math.max(r, az), v = aE();
        var z = (f > q ? (f - q) / 2 : at.minPadding) + v[1], c = (c > r ? (c - r) / 2 : at.minPadding) + v[0];
        v = r / 3, l = q - aA - 10, animCallback = function () {
            aO(), aL()
        }, aG(al, {width: v, height: l}), aG(am, {
            width: v,
            height: l
        }), at.animate && !m ? at.resizeSync ? V(ap, {
            width: r,
            left: c,
            height: q,
            top: z
        }, at.duration, animCallback) : V(ap, {width: r, left: c}, at.duration / 2, function () {
            V(ap, {height: q, top: z}, at.duration / 2, animCallback)
        }) : (aG(ap, {top: z, left: c, width: r, height: q}), animCallback())
    }

    function aL() {
        innerCont.style.display = "block", aG(an, {opacity: 0}), V(an, {opacity: 1}, at.fadeImage ? 400 : 0, function () {
            aw = 0
        })
    }

    function aK() {
        innerCont.style.display = "none"
    }

    function aJ() {
        at.enableZoom || (aj = ai = "");
        var d = aD(av, {id: "LG_container"}, ac, aD(av, {id: "LG_innerCont"}, aD(av, {id: "LG_panel"}, aj, ai, ak, ah, aD(av, {
            id: "LG_closeBtn",
            title: ar.close
        }), aD(av, {style: "clear:both"})), an, ao, am, al)), c = function (f) {
            f.cancelBubble = !0, f.stopPropagation && f.stopPropagation();
            var e = f.target || f.srcElement;
            "click" == f.type ? (f = {
                LG_closeBtn: Y.close,
                LG_zoomNormal: Y.zoomNormal,
                LG_fitScreen: Y.zoomNormal,
                LG_zoomIn: Y.zoomIn,
                LG_zoomOut: Y.zoomOut,
                LG_nextLink: Y.next,
                LG_prevLink: Y.prev
            }, e.id in f && f[e.id].call(Y)) : "LG_nextLink" != e.id && "LG_prevLink" != e.id || V(e, {opacity: "mouseout" == f.type ? 0 : 1})
        };
        return Z(d, "click", c), Z(d, "mouseover", c), Z(d, "mouseout", c), d
    }

    function aI(a) {
        if (ae) {
            a = a || aQ.event, a = a.keyCode ? a.keyCode : a.which ? a.which : a.charCode;
            var d = {110: Y.next, 98: Y.prev, 102: Y.zoomNormal, 43: Y.zoomIn, 45: Y.zoomOut, 27: Y.close};
            d[a] && d[a]()
        }
    }

    function aH(e, d) {
        for (var f in d) {
            e[f] = d[f]
        }
    }

    function aG(e, d) {
        for (var f in d) {
            setElemStyle(e, f, d[f])
        }
    }

    function aF() {
        var h, g, b, a;
        return g = aP.documentElement, a = aP.body, b = "CSS1Compat" === aP.compatMode ? g : a, aQ.innerHeight && aQ.scrollMaxY ? (h = a.scrollWidth, g = aQ.innerHeight + aQ.scrollMaxY) : a.scrollHeight > a.offsetHeight ? (h = a.scrollWidth, g = a.scrollHeight) : g && g.scrollHeight > g.offsetHeight ? (h = g.scrollWidth, g = g.scrollHeight) : (h = a.offsetWidth, g = a.offsetHeight), a = b.clientHeight, b = b.clientWidth, [h < b ? b : h, g < a ? a : g, b, a]
    }

    function aE() {
        var h = 0, g = 0, b = aP.body, a = aP.documentElement;
        return "number" == typeof aQ.pageYOffset ? (g = aQ.pageYOffset, h = aQ.pageXOffset) : b && (b.scrollLeft || b.scrollTop) ? (g = b.scrollTop, h = b.scrollLeft) : a && (a.scrollLeft || a.scrollTop) && (g = a.scrollTop, h = a.scrollLeft), [h, g]
    }

    function aD(b, n) {
        var k, j, m = aP.createElement(b), l = 2, i = arguments.length;
        if (n) {
            for (k in n) {
                j = n[k], typeof j == au && ("class" == k ? m.className = j : m.setAttribute(k, j))
            }
        }
        for (; l < i; l++) {
            typeof arguments[l] == au ? m.innerHTML += arguments[l] : m.appendChild(arguments[l])
        }
        return m
    }

    function aC() {
        if (!ax) {
            if (!aP.body) {
                return setTimeout(aC, 13)
            }
            ax = !0, Y.init()
        }
    }

    function aB() {
        if (!ay) {
            if (ay = !0, "complete" === aP.readyState) {
                return aC()
            }
            if (aP.addEventListener) {
                aP.addEventListener("DOMContentLoaded", function b() {
                    aP.removeEventListener("DOMContentLoaded", b, !1), aC()
                }, !1)
            } else {
                if (aP.attachEvent) {
                    aP.attachEvent("onreadystatechange", function b() {
                        "complete" === aP.readyState && (aP.detachEvent("onreadystatechange", b), aC())
                    });
                    var f = !1;
                    try {
                        f = null === b.frameElement
                    } catch (b) {
                    }
                    if (aP.documentElement.doScroll && f) {
                        var e = function () {
                            if (!ax) {
                                try {
                                    aP.documentElement.doScroll("left")
                                } catch (c) {
                                    return void setTimeout(e, 1)
                                }
                                aC()
                            }
                        };
                        e()
                    }
                }
            }
        }
    }

    var az, ay, ax, aw, ap, af, ae, ad, aa, aA = !1, av = "div", au = "string", at = {
        showOverlay: !0,
        overlayColor: "#000",
        overlayOpacity: 0.85,
        zoomStep: 0.2,
        animate: !0,
        duration: 800,
        resizeSync: !1,
        enableZoom: !0,
        fadeImage: !0,
        alias: "lightgallery",
        fullSize: !1,
        minPadding: 15,
        imageAttrib: "data-image"
    }, ar = {
        next: "Следующий слайд",
        prev: "Предыдущий слайд",
        zoomIn: "Увеличить",
        zoomOut: "Уменьшить",
        fullSize: "В весь размер",
        fitScreen: "В нормальный размер",
        close: "Закрыть",
        image: "",
        of: "/"
    }, aq = /MSIE ([^;]+)/.test(navigator.userAgent) && parseFloat(RegExp.$1), ao = aD(av, {id: "LG_titleBar"}), an = aD("img", {id: "LG_pic"}), am = aD(av, {
        id: "LG_prevLink",
        title: ar.prev
    }), al = aD(av, {id: "LG_nextLink", title: ar.next}), ak = aD(av, {
        id: "LG_zoomNormal",
        title: ar.fullSize
    }), aj = aD(av, {id: "LG_zoomIn", title: ar.zoomIn}), ai = aD(av, {
        id: "LG_zoomOut",
        title: ar.zoomOut
    }), ah = aD(av, {id: "LG_imgIndex"}, ar.image + " 20 " + ar.of + " 20 "), ag = aD("img"), ac = aD(av, {id: "LG_loading"}), ab = aD(av, {id: "LG_overlay"}), Z = function () {
        return aQ.addEventListener ? function (e, d, f) {
            e.addEventListener(d, f, !1)
        } : aQ.attachEvent ? function (a, f, e) {
            a.attachEvent("on" + f, function () {
                e.call(a, aQ.event)
            })
        } : void 0
    }(), Y = {
        setLangVars: function (b) {
            aH(ar, b)
        }, init: function (k) {
            if (k && aH(at, k), !ay) {
                return aB()
            }
            aa = [], k = aP.getElementsByTagName("a");
            for (var h, j, i = RegExp("^" + at.alias + "\\[([a-zA-Z0-9_-]+)\\]|" + at.alias + "$"), e = 0, b = k.length, a = aP.body; e < b; e++) {
                j = k[e], (h = j.getAttribute(at.imageAttrib) || j.getAttribute("rel")) && (h = h.match(i)) && (Z(j, "click", Y.showImage), (h = h[1]) && (aa[h] || (aa[h] = []), j.__gallery__ = h, j.__index__ = aa[h].push(j) - 1))
            }
            ab.onclick = Y.close, a.appendChild(ab), ap || (a.appendChild(ap = aJ()), innerCont = ap.lastChild), Z(a.attachEvent ? a : aQ, "keypress", aI), ag.onload = function () {
                ac.style.display = "none", an.src = ag.src, aM(at.fullSize ? 1 : 0, !0);
                var c = aa[af];
                c && c[ad + 1] && (aD("img").src = c[ad + 1].href), c && c[ad - 1] && (aD("img").src = c[ad - 1].href)
            }, !1 === aA && (aA = ap.offsetHeight, az = aq ? 200 : ap.offsetWidth), aG(ab, {
                background: at.overlayColor,
                display: "none",
                opacity: at.overlayOpacity
            })
        }, open: function () {
            ae || (aO(), an.style.display = "block", aM(), aG(ap, {visibility: "visible", display: "block"}), ae = 1)
        }, close: function () {
            aN(), aG(ap, {visibility: "hidden", display: "none"}), ag.src = an.src = ""
        }, zoomIn: function () {
            Y.Zoom(1 + at.zoomStep)
        }, zoomOut: function () {
            Y.Zoom(1 - at.zoomStep)
        }, zoomNormal: function () {
            this.$disabled || Y.Zoom(an.width == ag.width && an.height == ag.height ? 0 : 1)
        }, Zoom: function (b) {
            aK(), aM(b)
        }, showImage: function (a) {
            var d = this.__index__;
            a = a || aQ.event, a.returnValue = !1, a.preventDefault && a.preventDefault(), this.__gallery__ && -1 < d ? (af = this.__gallery__, Y.show(d)) : (af = null, Y.showSingle(this))
        }, showSingle: function (b) {
            Y.open(), aK(), ac.style.display = "block", ag.src = b.href, ao.innerHTML = b.title, ah.innerHTML = "", am.style.visibility = al.style.visibility = "hidden"
        }, show: function (a) {
            if (af && !(0 > a || a > aa[af].length - 1 || at.animate && aw)) {
                Y.open();
                var h = aa[af], g = al.style, f = am.style;
                aK(), ac.style.display = "block", aw = 1, ag.src = h[a].href, ao.innerHTML = h[a].title, ah.innerHTML = ar.image + " " + (a + 1) + " " + ar.of + " " + h.length, ad = a, g.visibility = ad < aa[af].length - 1 ? "visible" : "hidden", f.visibility = ad ? "visible" : "hidden", aQ.focus()
            }
        }, next: function () {
            Y.show(ad + 1)
        }, prev: function () {
            Y.show(ad - 1)
        }
    }, X = typeof aD("a").style.filter == au, W = function () {
        return aQ.requestAnimationFrame || aQ.webkitRequestAnimationFrame || aQ.mozRequestAnimationFrame || aQ.msRequestAnimationFrame || aQ.oRequestAnimationFrame || function (a) {
                aQ.setTimeout(function () {
                    a(+new Date)
                }, 17)
            }
    }();
    setElemStyle = function () {
        var b = {width: 1, height: 1, top: 1, left: 1};
        return X ? function (a, f, e) {
            "opacity" == f && (a.style.filter = "alpha(opacity=" + 100 * e + ")"), a.style[f] = e + (b[f] ? "px" : "")
        } : function (a, f, e) {
            a.style[f] = e + (b[f] ? "px" : "")
        }
    }();
    var V = function (F, E, D, C) {
        D = at.animate ? "number" == typeof D ? D : 200 : 0;
        var A, r, B = F.currentStyle ? F.currentStyle : getComputedStyle(F, null), z = {}, y = aQ.performance, x = y && (y.now || y.webkitNow || y.msNow || y.mozNow), w = x ? function () {
            return x.call(y)
        } : function () {
            return +new Date
        }, v = w(), u = v + D, t = !0, s = 0, a = function (b) {
            if (t) {
                x && 1000000000000 < b && (b = w());
                var d, c;
                c = b > u ? 1 : (b - v) / D;
                for (d in E) {
                    setElemStyle(F, d, (z[d] + (E[d] - z[d]) * (-Math.cos(c * Math.PI) / 2 + 0.5)).toFixed(3))
                }
                b > u && (t = !1, C && C()), W(a)
            }
        };
        for (A in E) {
            "opacity" == A && X ? (r = B.filter.match(/opacity\=(\d+)/)) && (s = parseFloat(r[1]) / 100) : s = parseFloat(B[A]), z[A] = s
        }
        W(a)
    };
    return Y
}(window, document);
lightgallery.init();
var AJAX = function () {
    var b = window.ActiveXObject ? new ActiveXObject("Microsoft.XMLHTTP") : new XMLHttpRequest;
    return {
        get: function (a, f, e) {
            b.open("GET", a), b.onreadystatechange = function () {
                4 == b.readyState && f.call(e || window, b)
            }, b.send(null)
        }
    }
}();