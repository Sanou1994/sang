(this["webpackJsonpje-reserve"]=this["webpackJsonpje-reserve"]||[]).push([[75],{668:function(e,t,n){"use strict";n.d(t,"m",(function(){return c})),n.d(t,"f",(function(){return a})),n.d(t,"d",(function(){return s})),n.d(t,"j",(function(){return u})),n.d(t,"k",(function(){return l})),n.d(t,"l",(function(){return d})),n.d(t,"p",(function(){return f})),n.d(t,"o",(function(){return m})),n.d(t,"n",(function(){return b})),n.d(t,"b",(function(){return j})),n.d(t,"a",(function(){return y})),n.d(t,"i",(function(){return g})),n.d(t,"c",(function(){return v})),n.d(t,"h",(function(){return x})),n.d(t,"g",(function(){return N})),n.d(t,"e",(function(){return A}));var r,o=n(71),i=n.n(o);function c(e){document.body.style.paddingRight=e>0?e+"px":null}function a(){var e=window.getComputedStyle(document.body,null);return parseInt(e&&e.getPropertyValue("padding-right")||0,10)}function s(){var e=function(){var e=document.createElement("div");e.style.position="absolute",e.style.top="-9999px",e.style.width="50px",e.style.height="50px",e.style.overflow="scroll",document.body.appendChild(e);var t=e.offsetWidth-e.clientWidth;return document.body.removeChild(e),t}(),t=document.querySelectorAll(".fixed-top, .fixed-bottom, .is-fixed, .sticky-top")[0],n=t?parseInt(t.style.paddingRight||0,10):0;document.body.clientWidth<window.innerWidth&&c(n+e)}function u(e,t){return void 0===e&&(e=""),void 0===t&&(t=r),t?e.split(" ").map((function(e){return t[e]||e})).join(" "):e}function l(e,t){var n={};return Object.keys(e).forEach((function(r){-1===t.indexOf(r)&&(n[r]=e[r])})),n}function d(e,t){for(var n,r=Array.isArray(t)?t:[t],o=r.length,i={};o>0;)i[n=r[o-=1]]=e[n];return i}var p={};function f(e){p[e]||("undefined"!==typeof console&&console.error(e),p[e]=!0)}var h="object"===typeof window&&window.Element||function(){};var m=i.a.oneOfType([i.a.string,i.a.func,function(e,t,n){if(!(e[t]instanceof h))return new Error("Invalid prop `"+t+"` supplied to `"+n+"`. Expected prop to be an instance of Element. Validation failed.")},i.a.shape({current:i.a.any})]),b=i.a.oneOfType([i.a.func,i.a.string,i.a.shape({$$typeof:i.a.symbol,render:i.a.func}),i.a.arrayOf(i.a.oneOfType([i.a.func,i.a.string,i.a.shape({$$typeof:i.a.symbol,render:i.a.func})]))]),j={Fade:150,Collapse:350,Modal:300,Carousel:600},y=["in","mountOnEnter","unmountOnExit","appear","enter","exit","timeout","onEnter","onEntering","onEntered","onExit","onExiting","onExited"],g={esc:27,space:32,enter:13,tab:9,up:38,down:40,home:36,end:35,n:78,p:80},v=!("undefined"===typeof window||!window.document||!window.document.createElement);function O(e){return null==e?void 0===e?"[object Undefined]":"[object Null]":Object.prototype.toString.call(e)}function x(e){var t=typeof e;return null!=e&&("object"===t||"function"===t)}function E(e){if(function(e){return!(!e||"object"!==typeof e)&&"current"in e}(e))return e.current;if(function(e){if(!x(e))return!1;var t=O(e);return"[object Function]"===t||"[object AsyncFunction]"===t||"[object GeneratorFunction]"===t||"[object Proxy]"===t}(e))return e();if("string"===typeof e&&v){var t=document.querySelectorAll(e);if(t.length||(t=document.querySelectorAll("#"+e)),!t.length)throw new Error("The target '"+e+"' could not be identified in the dom, tip: check spelling");return t}return e}function w(e){return null!==e&&(Array.isArray(e)||v&&"number"===typeof e.length)}function N(e,t){var n=E(e);return t?w(n)?n:null===n?[]:[n]:w(n)?n[0]:n}var A=["a[href]","area[href]","input:not([disabled]):not([type=hidden])","select:not([disabled])","textarea:not([disabled])","button:not([disabled])","object","embed","[tabindex]:not(.modal)","audio[controls]","video[controls]",'[contenteditable]:not([contenteditable="false"])']},670:function(e,t,n){"use strict";t.a={type_tournoi:{TOURNOI_INTER_ECOLE:"TOURNOI_INTER_ECOLE",TOURNOI_INTER_ENTREPRISE:"TOURNOI_INTER_ENTREPRISE"},url:"https://jereserve.herokuapp.com/"}},723:function(e,t,n){"use strict";n.d(t,"a",(function(){return o}));var r=n(706);n(733);r.a.initializeApp({apiKey:"AIzaSyAEAU3GMvWGKcakyDnkICPmkVZaDCFRX08",authDomain:"oterrain-6822f.firebaseapp.com",projectId:"oterrain-6822f",storageBucket:"oterrain-6822f.appspot.com",messagingSenderId:"309004801581",appId:"1:309004801581:web:a535282f9a8c79328e0aa4",measurementId:"G-DZWQ4BHVY3"});var o=r.a.storage()},841:function(e,t,n){"use strict";var r=n(14),o=n(27),i=n(1),c=n.n(i),a=n(71),s=n.n(a),u=n(178),l=n.n(u),d=n(668),p=["className","cssModule","type","size","color","children","tag"],f={tag:d.n,type:s.a.string,size:s.a.string,color:s.a.string,className:s.a.string,cssModule:s.a.object,children:s.a.string},h=function(e){var t=e.className,n=e.cssModule,i=e.type,a=e.size,s=e.color,u=e.children,f=e.tag,h=Object(o.a)(e,p),m=Object(d.j)(l()(t,!!a&&"spinner-"+i+"-"+a,"spinner-"+i,!!s&&"text-"+s),n);return c.a.createElement(f,Object(r.a)({role:"status"},h,{className:m}),u&&c.a.createElement("span",{className:Object(d.j)("sr-only",n)},u))};h.propTypes=f,h.defaultProps={tag:"div",type:"border",children:"Loading..."},t.a=h},950:function(e,t,n){"use strict";n.r(t);var r=n(179),o=n(180),i=n(181),c=n(183),a=n(1),s=n(841),u=n(673),l=n(670),d=n(723),p=n(3),f=Object(p.jsx)("div",{className:"pt-3 text-center",children:Object(p.jsx)("div",{className:"sk-spinner sk-spinner-pulse"})}),h=function(e){Object(i.a)(n,e);var t=Object(c.a)(n);function n(e){var o;return Object(r.a)(this,n),(o=t.call(this,e)).state={textApropos:"",ok:"",titreApropos:"",idApropos:0,telephone:"",email:"",adresse:"",service:[],video:"",video_joutube:"",page:Object(p.jsx)("div",{style:{marginTop:"25%",marginBottom:"20%",textAlign:"center"},children:Object(p.jsx)(s.a,{type:"grow",color:"black"})}),status:!0},o}return Object(o.a)(n,[{key:"componentDidMount",value:function(){var e=this,t={method:"GET"};fetch(l.a.url+"acceuil/propos/propos",t).then((function(e){return e.json()})).then((function(t){console.log("propos====>",t),e.setState({status:!1});try{e.setState({titreApropos:t[0].titre}),e.setState({textApropos:t[0].text}),e.setState({adresse:t[0].adresse}),e.setState({telephone:t[0].telephone}),e.setState({email:t[0].email}),e.setState({video_joutube:t[0].urlVideo}),e.setState({idApropos:t[0].id}),e.setState({status:!1})}catch(n){}})),fetch(l.a.url+"acceuil/fichier/fichiers",t).then((function(e){return e.json()})).then((function(t){console.log("fichier====>",t),t.map((function(t,n){try{t.multimedia&&d.a.ref("images").child(t.multimedia.file).getDownloadURL().then((function(n){"VIDEO_PRESENTATION"===t.typeFichier&&(e.setState({video:n}),console.log("fichierZErrr====>",n))}))}catch(r){e.setState({video:""})}}))}))}},{key:"render",value:function(){return Object(p.jsxs)(p.Fragment,{children:[this.state.ok,Object(p.jsx)(u.g,{className:"mt-5",fluid:!0,children:Object(p.jsx)(a.Suspense,{fallback:f,children:this.state.status?Object(p.jsx)(p.Fragment,{children:this.state.page}):Object(p.jsxs)(u.m,{children:[Object(p.jsx)("div",{className:"mt-5"}),Object(p.jsxs)("section",{id:"about",class:"about",children:[Object(p.jsx)("div",{class:"section-title mt-5",children:Object(p.jsx)("h2",{children:"A propos"})}),Object(p.jsxs)("div",{class:"row",children:[Object(p.jsx)("div",{class:"col-lg-6 col-md-12 col-sm-12 order-1 order-lg-2",children:Object(p.jsx)("div",{className:"embed-responsive embed-responsive-16by9 mb-4",children:Object(p.jsx)("iframe",{className:"embed-responsive-item",src:this.state.video,height:"100",width:"200",allowfullscreen:!0,frameborder:"4"})})}),Object(p.jsxs)("div",{class:"col-lg-6 col-md-12 col-sm-12 pt-4 pt-lg-0 order-2 order-lg-1 content",children:[Object(p.jsx)("h3",{class:"text-center",children:this.state.titreApropos}),Object(p.jsx)("p",{class:"text-justify",children:this.state.textApropos})]})]})]})]})})})]})}}]),n}(a.Component);t.default=h}}]);
//# sourceMappingURL=75.e3a428f8.chunk.js.map