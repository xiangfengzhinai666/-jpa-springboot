# -jpa-springboot
使用jpa时，一直显示UserRepository类不存在，里面有一个username属性，但是写User类的时候没有把该属性给加上去，导致报此错误。加上username属性，错误就解决了



登录的编写，加了拦截器之后有点bug,比如图片加载不出来，会跳转到错误页面

admin中标签高亮显示有问题 





报错：Caused by: org.[hibernate](https://so.csdn.net/so/search?q=hibernate&spm=1001.2101.3001.7020).tool.schema.extract.spi.SchemaExtractionException: More than one table found in namespace (, ) : t_user



在properties文件下加上：

```properties
这一行写的是自己的数据库名
spring.jpa.properties.hibernate.default_catalog=blog
这一行不知道在干什么
spring.jpa.properties.hibernate.default_schema=yourSchema
```

就可以创建出这张表



把blogs-input这里，的type.id改成typeId了，目前还可以进行校验，如果后序出现什么bug再改回去，



第36节，前面10分钟主要是讲up自己私下改的bug,后边才是前端展示

p37,这个是因为36没有添加desciption属性导致的，把这个属性需要的th给去掉了

描述属性还必须要加上，要不然博客显示的文本都是单一的（这里需要在其加上desciption属性，前端页面应该没啥问题了）

详情页里面的有一个不显示浏览次数，不知道是怎么回事（把那个给删了，其他的都显示）

现在主页里面的内容都是一样的，需要处理一下下



p40 评论表单校验出现问题 一样的代码up可以校验全部，我的只能校验content，邮箱和姓名不能被校验（有点问题），我发现了虽然邮箱和姓名窗口不报红，但是如果为空值的话也是显示的校验失败。



p42报错Caused by: java.lang.NumberFormatException: For input string: "comment"

导致评论的头像显示不出来，可能评论刷新页面消失也和这个有关，明天对照着视频排查（显示出来了）

现在是管理员可以评论，并且也和其他的做了区别，但是管理员的头像和其他的一样



p44都说写的上一页和下一页有问题



tag这个点击报错很正常，因为我没有写对应的属性

归档页面其他都还行，就是标签的右边显示不出来原创标签





p47底部的最新博客加载不出来，中英文切换的配置文件没有添加



添加插件aplayer 引入css和js,别忘了把这个放到想要播放音乐的页面里

```
<!--    mad我说为啥没有，要把这个div放到想要播放音乐的页面里-->
    <div id="aplayer"></div>
```



这个使用第三方插件集成，我用的连接到了网易云，网页版把相对应的id输入上即可，但是“我喜欢”的可能太多，导致其加载不出来，换成年度歌单音乐少一点就可以

```
<meting-js
        server="netease"  //网易云
        type="playlist" //歌单类型
        id="7903738915" //歌单id
        fixed="true" //mini播放器
        mini="true"	//mini播放一般在左下角
        order="random" //随机
        loop="all" //
        preload="auto"
        list-folded="false"
        autoplay="true" //自动播放，把这个设置成true也不能自动播放，可能是浏览器本来就不支持这个东西，问题不大>
</meting-js>
播放音乐，把模板里的页面都改成新页面打开了，要不然音乐会断，
把这个模板移动到_fragment里面发现歌曲名看不见了，只有选中才能看到，作者却不受影响
我不理解
```

樱花特效有点问题电脑没电了，等会再看（完成，樱花直接把js文件放入到项目了，然后直接<script>引入即可，注意引入的地址）

鼠标点击插件引入，

看板娘引入（直接拷贝的大佬的git文件，不知道不挂梯子速度怎么样，点击其中的按钮会跳到大佬的git上，想自己修改一些话，但都是js代码，因此放弃）



后端的标签跳转不过去，前端的路径有问题，部署不到服务器。不知道前端的标签可不可以访问到（前端的标签能跳转了，后端还是不行）

项目的前端更改大概就这样了，就剩下后端标签页面不能跳转

还有我想把index页面设计的更加好看一点（看看能不能找到模板）

    
    
![首页](https://user-images.githubusercontent.com/86298280/223313728-ed811923-5b80-45ea-a278-2be3ed2a0356.png)
