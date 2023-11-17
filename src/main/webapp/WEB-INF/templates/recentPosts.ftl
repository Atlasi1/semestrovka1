<#macro recent>
    <div class="col-md-4">
        <div class="position-sticky" style="top: 2rem;">
            <div>
                <h4 class="fst-italic">Недавние посты</h4>
                <ul class="list-unstyled">
                    <#list listOfRecentPosts as rp>
                        <li>
                            <a class="d-flex flex-column flex-lg-row gap-3 align-items-start align-items-lg-center py-3 link-body-emphasis text-decoration-none border-top"
                               href="/post?post_id=${rp.getId()}">
                                <#if rp.getFilename()??>
                                    <svg class="bd-placeholder-img" width="100%" height="96"
                                         xmlns="http://www.w3.org/2000/svg" aria-hidden="true"
                                         preserveAspectRatio="xMidYMid slice" focusable="false">
                                        <image xlink:href="${rp.getFilename()!}" width="100%" height="100%"/>
                                    </svg>
                                <#else>
                                    <svg class="bd-placeholder-img" width="100%" height="96"
                                         xmlns="http://www.w3.org/2000/svg" aria-hidden="true"
                                         preserveAspectRatio="xMidYMid slice" focusable="false">
                                        <rect width="100%" height="100%" fill="#777"></rect>
                                    </svg>
                                </#if>
                                <div class="col-lg-8">
                                    <h6 class="mb-0">${rp.getTitle()}</h6>
                                    <small class="text-body-secondary">${rp.getDate()}</small>
                                </div>
                            </a>
                        </li>
                    </#list>
                </ul>
            </div>
        </div>
    </div>
</#macro>