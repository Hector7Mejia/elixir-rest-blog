import createView from "../createView.js";

export default function Logout(props) {
    console.log("Logging out");
    return ``;
}

export function LogoutEvents() {
    $("#notification-box").text("Hey, a message");
    console.log("Calling LogoutEvents...");
    window.localStorage.removeItem("access_token");
    window.localStorage.removeItem("refresh_token");
    createView("/login");
}