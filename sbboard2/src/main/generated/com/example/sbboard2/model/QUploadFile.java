package com.example.sbboard2.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUploadFile is a Querydsl query type for UploadFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUploadFile extends EntityPathBase<UploadFile> {

    private static final long serialVersionUID = -1883860252L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUploadFile uploadFile = new QUploadFile("uploadFile");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QBoard board;

    public final StringPath filename = createString("filename");

    public final NumberPath<Long> fno = createNumber("fno", Long.class);

    public final BooleanPath image = createBoolean("image");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath uuid = createString("uuid");

    public QUploadFile(String variable) {
        this(UploadFile.class, forVariable(variable), INITS);
    }

    public QUploadFile(Path<? extends UploadFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUploadFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUploadFile(PathMetadata metadata, PathInits inits) {
        this(UploadFile.class, metadata, inits);
    }

    public QUploadFile(Class<? extends UploadFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

