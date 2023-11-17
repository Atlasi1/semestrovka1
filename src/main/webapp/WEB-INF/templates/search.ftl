<#include "base.ftl"/>
<#import "_header.ftl" as header/>

<#macro title>
    Поиск
</#macro>

<#macro content>
    <@header.header/>
    <div class="container">
        <form method="get">
            <div class="row">
                <div class="col-md-4">
                    <div class="mb-3">
                        <label for="postTitle" class="form-label">Post Title</label>
                        <input type="text" class="form-control" id="postTitle">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="mb-3">
                        <label for="author" class="form-label">Author</label>
                        <input type="text" class="form-control" id="author">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="mb-3">
                        <label for="date" class="form-label">Date</label>
                        <input type="date" class="form-control" id="date">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <div class="mb-3">
                        <label for="category" class="form-label">Category</label>
                        <select class="form-select" id="category">
                            <option value="">All</option>
                            <option value="category1">Category 1</option>
                            <option value="category2">Category 2</option>
                            <option value="category3">Category 3</option>
                        </select>
                    </div>
                </div>
            </div>
        </form>
    </div>
</#macro>