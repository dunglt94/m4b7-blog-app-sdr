function searchBlogs() {
    event.preventDefault();
    let title = $("#search").val();
    $.ajax({
        url: `http://localhost:8080/api/blogs/search?q=${title}`,
        type: "GET",
        success: function (data) {
            console.log("Data received:", data);
            let blogs = data.content;

            let content = "";
            for (let i = 0; i < blogs.length; i++) {
                content += `
                    <tr>
                        <td>${i + 1}</td>
                        <td>
                            <a href="/blogs/${blogs[i].id}/view">${blogs[i].title}</a>
                        </td>
                        <td id="content">${blogs[i].content}</td>
                        <td>${blogs[i].category.name}</td>
                        <td>
                            ${blogs[i].publishedDate[2]}/${ blogs[i].publishedDate[1]}/${blogs[i].publishedDate[0]}
                        </td>
                        <td><button type="button" class="edit" value="${blogs[i].id}">Edit</button></td>
                        <td><button type="button" class="delete" value="${blogs[i].id}">Delete</button></td>
                    </tr>`;
            }
            $("#blogs").html(content);
        }
    });
}

let currentPage = 0;
let pageSize = 4;
function loadMore() {
    event.preventDefault();
    currentPage++;
    $.ajax({
        url: `http://localhost:8080/api/blogs?page=${currentPage}`,
        type: "GET",
        success: function (data) {
            console.log("Data received:", data);
            let blogs = data.content;
            let content = "";
            for (let i = 0; i < blogs.length; i++) {
                content += `
                    <tr>
                        <td>${i + 1 + currentPage * pageSize}</td>
                        <td>
                            <a href="/blogs/${blogs[i].id}/view">${blogs[i].title}</a>
                        </td>
                        <td id="content">${blogs[i].content}</td>
                        <td>${blogs[i].category.name}</td>
                        <td>
                            ${blogs[i].publishedDate[2]}/${ blogs[i].publishedDate[1]}/${blogs[i].publishedDate[0]}
                        </td>
                        <td><button type="button" class="edit" value="${blogs[i].id}">Edit</button></td>
                        <td><button type="button" class="delete" value="${blogs[i].id}">Delete</button></td>
                    </tr>`;
            }
            $("#blogs").append(content);
        }
    });
}