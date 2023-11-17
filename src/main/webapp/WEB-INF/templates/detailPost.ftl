<#include "base.ftl"/>
<#import "_header.ftl" as header/>
<#import "recentPosts.ftl" as recentPosts/>

<#macro content>
    <@header.header/>
    <div class="container">
        <div class="row g-5">
            <div class="col-md-8">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <div class="row">
                            <div class="d-flex justify-content-start">
                                <#if post.getCreatorPhoto()??>
                                    <img class="rounded-circle" width="40" height="40" src="${post.getCreatorPhoto()!}">
                                <#else>
                                    <img class="rounded-circle" width="40" height="40" src="/icons/user.png">
                                </#if>
                                <span> ${post.getCreatorName()}</span>
                            </div>
                        </div>
                    </div>
                    <#if post.getFilename()??>
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="300"
                             xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail"
                             preserveAspectRatio="xMidYMid slice" focusable="false">
                            <image xlink:href="${post.getFilename()!}" width="100%" height="100%"/>
                        </svg>
                    <#else>
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="300"
                             xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail"
                             preserveAspectRatio="xMidYMid slice" focusable="false">
                            <text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text>
                        </svg>
                    </#if>
                    <div class="card-body">
                        <p class="card-text">${post.getContent()}</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <#if user??>
                                    <button type="button" id="buttonLike" class="btn btn-sm btn-outline-secondary"
                                            onclick="f()">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-suit-heart" viewBox="0 0 16 16">
                                            <path d="m8 6.236-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z"></path>
                                        </svg>
                                        <p id="countLike">${post.getCountOfLikes()}</p>
                                    </button>
                                <#else>
                                    <button disabled type="button" id="buttonLike"
                                            class="btn btn-sm btn-outline-secondary">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-suit-heart" viewBox="0 0 16 16">
                                            <path d="m8 6.236-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z"></path>
                                        </svg>
                                        <span id="countLike">${post.getCountOfLikes()}</span>
                                    </button>
                                </#if>
                                <button disabled type="button" class="btn btn-sm btn-outline-secondary">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-chat" viewBox="0 0 16 16">
                                        <path d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"></path>
                                    </svg>
                                    ${post.getCountOfComments()}
                                </button>
                            </div>
                            <small class="text-body-secondary">${post.getDate()}</small>
                        </div>
                    </div>
                </div>
                <br>
                <div class="d-flex flex-column comment-section">
                    <#list post.getComments() as comment>
                        <div class="bg-white p-2">
                            <div class="d-flex flex-row user-info"><img class="rounded-circle"
                                                                        src="${comment.getWriter().getPhoto()!}"
                                                                        width="40" height="40">
                                <div class="d-flex flex-column justify-content-start ml-2"><span
                                            class="d-block font-weight-bold name">${comment.getWriter().getUsername()}</span>
                                </div>
                            </div>
                            <div class="mt-2">
                                <p class="comment-text">${comment.getText()}</p>
                            </div>
                        </div>
                    </#list>
                    <#if user??>
                        <form method="post">
                            <div class="bg-light p-2">
                                <#if user.getPhoto()??>
                                    <div class="d-flex flex-row align-items-start"><img class="rounded-circle"
                                                                                        src="${user.getPhoto()!}"
                                                                                        width="40" height="40">
                                        <input type="text" name="text"
                                               class="form-control ml-1 shadow-none textarea">
                                        <input type="hidden" name="post_id" value="${post.getId()}">
                                    </div>
                                <#else>
                                    <div class="d-flex flex-row align-items-start"><img class="rounded-circle"
                                                                                        src="/icons/user.png"
                                                                                        width="40" height="40">
                                        <input type="text" name="text"
                                               class="form-control ml-1 shadow-none textarea">
                                        <input type="hidden" name="post_id" value="${post.getId()}">
                                    </div>
                                </#if>
                                <div class="mt-2 text-right">
                                    <input type="submit" class="btn btn-primary btn-sm shadow-none"
                                           value="Комментировать">
                                </div>
                            </div>
                        </form>
                    <#else>
                        <div class="bg-light p-2">
                            <p class="text-secondary">Вы не вошли в аккаунт.</p>
                        </div>
                        <p id="error"></p>
                    </#if>
                </div>
            </div>
            <@recentPosts.recent/>
        </div>
    </div>
    <#if user??>
        <script type="application/javascript">
            function f() {
                $(document).ready(function () {
                        $.ajax({
                            url: "/like",
                            type: "POST",
                            data: {post_id: ${post.getId()}, user_id: ${user.getId()}},
                            success: function () {
                                $("countLike").val = String(Number($("countLike").val) + 1);
                            },
                            error: function (xhr, status, error) {
                                console.log(error);
                            }
                        });
                    }
                )
                ;
            }
        </script>
    </#if>
</#macro>

<#macro title>
    ${post.getTitle()}
</#macro>

<@main/>