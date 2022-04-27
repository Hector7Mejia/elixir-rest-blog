import createView from "../createview.js";

export default function UserIndex(props) {
    return `
    <div>
        <h1 id="show-id">${props.users.id}</h1>
        <h1 id="show-userename">${props.users.username}</h1>
        <h1 id="show-email">${props.users.email}</h1>
    </div>
    
    <form>
    <label>Update password</label>
    <input class="update-password" type="password" id="update-password-input" data-id="${props.users.id}">
        <button class="update-password-btn" type="button" id="update-password-button" data-id="${props.users.id}">Submit</button>
</form>
</form>
    `
}
const BASE_URI = "http://localhost:8080/api/users";
export function UserEvents() {
    $("#update-password-button").click(function () {
        // 1. grab data from form fields
        const id = $(this).data("id");
        let uriExtra = '/' + id + '/updatePassword';
        // const oldPassword = $("#old-password").val()
        const newPassword = $("#update-password-input").val()
        console.log(newPassword)


        // 2. assemble the request
        const request = {
            method: "PUT"
        }


        // 3. do the fetch with the correct URI please (check against Postman)
        fetch(`${BASE_URI}${uriExtra}?newPassword=${newPassword}`, request)
            .then(res => {
                console.log(`${request.method} SUCCESS: ${res.status}`);
            }).catch(error => {
            console.log(`${request.method} ERROR: ${error}`);
        }).finally(() => {
            createView("/users");
        });

    });
}