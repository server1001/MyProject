Glide

Glide  with 方法 创建一个架加载图片的实例  返回一个    RequestManager 对象
从源码中可以查看 with方法经过了多次重载 参数可传Context、Activity、FragmentActivity、Fragment等
总的来说with()的参数可分为Application 与非Application 皆是为了生命周期同步
在with方法中  使用getRetriever（）方法获取一个RequestManagerRetriever对象然后在调用其静态get()
方法获取RequestManager对象，在 getRetriever（） 方法中对Glide进行了初始化



  @NonNull
  public static RequestManager with(@NonNull Context context) {
    return getRetriever(context).get(context);
  }


  @NonNull
  private static RequestManagerRetriever getRetriever(@Nullable Context context) {
    // Context could be null for other reasons (ie the user passes in null), but in practice it will
    // only occur due to errors with the Fragment lifecycle.
    Preconditions.checkNotNull(
        context,
        "You cannot start a load on a not yet attached View or a Fragment where getActivity() "
            + "returns null (which usually occurs when getActivity() is called before the Fragment "
            + "is attached or after the Fragment is destroyed).");
    return Glide.get(context).getRequestManagerRetriever();
  }
  在getRetriever() 方法中调用了Glide的静态方法get()对Glide进行了初始化
  得到Glide 实例后再通过getRequestManagerRetriever()获取requestManagerRetriever对象
  至此 with方法结束

  Glide 实例化

    @NonNull
  public static Glide get(@NonNull Context context) {
    if (glide == null) { // DCL模式
      synchronized (Glide.class) {
        if (glide == null) {
          checkAndInitializeGlide(context);
        }
      }
    }

    return glide;
  }

  一路下行会发现最终执行到了Glide的 initializeGlide 方法中
    private static void initializeGlide(@NonNull Context context, @NonNull GlideBuilder builder) {
    //省略部分代码 此处对Glide 进行了新建

    //build() 获取Glide 配置项 并实例化
    Glide glide = builder.build(applicationContext);
    for (com.bumptech.glide.module.GlideModule module : manifestModules) {
      module.registerComponents(applicationContext, glide, glide.registry);
    }
    if (annotationGeneratedModule != null) {
      annotationGeneratedModule.registerComponents(applicationContext, glide, glide.registry);
    }
    applicationContext.registerComponentCallbacks(glide);
    Glide.glide = glide;
  }

    @NonNull
  Glide build(@NonNull Context context) {
  //省略部分代码
    RequestManagerRetriever requestManagerRetriever =
        new RequestManagerRetriever(requestManagerFactory);

    return new Glide(
        context,
        engine,
        memoryCache,
        bitmapPool,
        arrayPool,
        requestManagerRetriever,
        connectivityMonitorFactory,
        logLevel,
        defaultRequestOptions.lock(),
        defaultTransitionOptions,
        defaultRequestListeners,
        isLoggingRequestOriginsEnabled);
  }


load()方法
load ()方法参数有多种 String、Uri、File、Integer (资源ID)、URL、byte[]等类型
我们从load(@Nullable String string) 开始了解

@NonNull
  @CheckResult
  @Override
  public RequestBuilder<Drawable> load(@Nullable String string) {
    return asDrawable().load(string);
  }

  asDrawable()方法返回一个RequestBuilder对象

  @NonNull
    @CheckResult
    public RequestBuilder<Drawable> asDrawable() {
      return as(Drawable.class);
    }




