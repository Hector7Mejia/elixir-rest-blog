export default function PostIndex(props) {
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
             <div>
                   <form>
                   <div class="form-group">
    <label for="exampleInputTitle">Enter Title</label>
    <input type="title" class="add-title" id="exampleInputTitle" aria-describedby="emailHelp" placeholder="Title">
<!--    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
  </div>
  <div class="form-group">
    <label for="exampleInputPost">Enter Post</label>
    <input type="email" class="form-control" id="exampleInputPost" aria-describedby="emailHelp" placeholder="Hi..">
<!--    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
  </div>
  
  <button type="submit" class="add-post-button">Add New Post</button>
</form>
</div>
           <div id="posts-container">
    ${props.posts.map(post =>
        `
           <h3>${post.title}</h3> 
            <h3>${post.content}</h3>
           /* This is where you may want to add the post's content, etc*/
       
        `)
        .join('')}
</div>

            
        </main>
    `;
}
// <p>${post.title}</p>


export function PostsEvent() {
    createAddPostListener();
}

function createAddPostListener() {
    console.log("adding add post listener");
    $("add-post-button").click(function () {
        const title = $(("add-post-button").val());
        const content = $(("add-title").val())
        const newPost = {
        title,
    content
    }
        console.log("Ready to add " + newPost)
    });
}