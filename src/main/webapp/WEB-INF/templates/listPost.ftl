<#include "base.ftl"/>
<#import "_header.ftl" as header/>
<#import "recentPosts.ftl" as recentPosts/>

<#macro title>
    Posts
</#macro>

<#macro content>
    <@header.header/>
    <div class="container">
        <div id="carouselExampleCaptions" class="carousel slide">
            <div class="carousel-indicators">
                <#list 1..countOfIntrestingPosts as x>
                    <#if x == 1>
                        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0"
                                class="active"
                                aria-current="true" aria-label="Slide 1"></button>
                        <#continue>
                    </#if>
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="${x - 1}"
                            aria-label="Slide ${x}"></button>
                </#list>
            </div>
            <div class="carousel-inner">
                <#list listOfIntrestingPosts as list>
                    <#if list_index == 0>
                        <div class="carousel-item active">
                            <#if list.getFilename()??>
                                <img src="${list.getFilename()!}" class="d-block w-100" height="1000" alt="Post's Photo">
                            <#else>
                                <img src="/photos/black-texture.jpg" class="d-block w-100" height="1000" alt="...">
                            </#if>
                            <div class="carousel-caption d-none d-md-block">
                                <h5>${list.getTitle()}</h5>
                                <p>${list.getPeaceOfContent()}</p>
                            </div>
                        </div>
                        <#continue>
                    </#if>
                    <div class="carousel-item">
                        <#if list.getFilename()??>
                            <img src="${list.getFilename()!}" class="d-block w-100" height="1000" alt="Post's Photo">
                        <#else>
                            <img src="/photos/black-texture.jpg" class="d-block w-100" height="1000" alt="...">
                        </#if>
                        <div class="carousel-caption d-none d-md-block">
                            <h5>${list.getTitle()}</h5>
                            <p>${list.getPeaceOfContent()}</p>
                        </div>
                    </div>
                </#list>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
                    data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
                    data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        <div class="row g-5">
            <div class="col-md-8" id="posts">
                <#list posts as post>
                    <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                        <div class="col p-4 d-flex flex-column position-static">
                            <strong class="d-inline-block mb-2 text-primary-emphasis">${post.getCategoryName()}</strong>
                            <h3 class="mb-0">${post.getTitle()}</h3>
                            <div class="mb-1 text-body-secondary">${post.getDate()}</div>
                            <p class="card-text mb-auto">${post.getPeaceOfContent()}</p>
                            <a href="/post?post_id=${post.getId()}"
                               class="icon-link gap-1 icon-link-hover">
                                Продолжить читать
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-right" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"/>
                                </svg>
                            </a>
                            <br>
                            <div class="d-grid gap-2 d-md-block">
                                <button type="button" class="btn btn-secondary" disabled>
                                    <#if user??>
                                        <#if likeService.isLiked(user.getId(), post.getId())>
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" style="color: red;" class="bi bi-suit-heart-fill" viewBox="0 0 16 16">
                                                <path d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z"/>
                                            </svg>
                                        </#if>
                                    <#else>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-suit-heart" viewBox="0 0 16 16">
                                            <path d="m8 6.236-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z"></path>
                                        </svg>
                                    </#if>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-suit-heart" viewBox="0 0 16 16">
                                        <path d="m8 6.236-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z"></path>
                                    </svg>
                                    ${post.getCountOfLikes()}
                                </button>
                                <button type="button" class="btn btn-secondary" disabled>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-dots" viewBox="0 0 16 16">
                                        <path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
                                        <path d="m2.165 15.803.02-.004c1.83-.363 2.948-.842 3.468-1.105A9.06 9.06 0 0 0 8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6a10.437 10.437 0 0 1-.524 2.318l-.003.011a10.722 10.722 0 0 1-.244.637c-.079.186.074.394.273.362a21.673 21.673 0 0 0 .693-.125zm.8-3.108a1 1 0 0 0-.287-.801C1.618 10.83 1 9.468 1 8c0-3.192 3.004-6 7-6s7 2.808 7 6c0 3.193-3.004 6-7 6a8.06 8.06 0 0 1-2.088-.272 1 1 0 0 0-.711.074c-.387.196-1.24.57-2.634.893a10.97 10.97 0 0 0 .398-2z"/>
                                    </svg>
                                    ${post.getCountOfComments()}
                                </button>
                            </div>
                        </div>
                        <div class="col-auto d-none d-lg-block">
                            <#if post.getFilename()??>
                                <svg class="bd-placeholder-img" width="200" height="250"
                                     xmlns="http://www.w3.org/2000/svg"
                                     role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice"
                                     focusable="false"><title>Placeholder</title>
                                    <image xlink:href="${post.getFilename()!}" width="100%" height="100%"/>
                                </svg>
                            <#else>
                                <svg class="bd-placeholder-img" width="200" height="250"
                                     xmlns="http://www.w3.org/2000/svg"
                                     role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice"
                                     focusable="false"><title>Placeholder</title>
                                    <rect width="100%" height="100%" fill="#55595c"></rect>
                                </svg>
                            </#if>
                        </div>
                    </div>
                </#list>
            </div>
            <@recentPosts.recent/>
        </div>
        <div id="navigation">
            <nav aria-label="Navigation for posts">
                <ul class="pagination">
                    <#if currentPage != 1>
                        <li class="page-item"><a class="page-link"
                                                 href="/?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
                        </li>
                    </#if>
                    <#list 1..noOfPages as i>
                        <#assign x = i>
                        <#switch x>
                            <#case currentPage>
                                <li class="page-item active"><a class="page-link">
                                        ${x} <span class="sr-only">(current)</span></a>
                                </li>
                                <#break>
                            <#default>
                                <li class="page-item"><a class="page-link"
                                                         href="/?recordsPerPage=${recordsPerPage}&currentPage=${x}">${x}</a>
                                </li>
                        </#switch>
                    </#list>
                    <#if currentPage < noOfPages>
                        <li class="page-item"><a class="page-link"
                                                 href="/?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                        </li>
                    </#if>
                </ul>
            </nav>
        </div>
    </div>
    <script>
        $(function () {
            if ($('#posts').text().trim() === '') {
                $('#navigation').hide();
            }
        });
    </script>
</#macro>

<@main/>