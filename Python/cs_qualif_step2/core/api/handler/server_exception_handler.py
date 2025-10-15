from fastapi import Request, status
from fastapi.responses import JSONResponse

from cs_qualif_step2.core.api.handler.response.exception_response import ExceptionResponse

async def server_exception_handler(request: Request, exc: Exception) -> JSONResponse:
    return JSONResponse(
        status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
        content=ExceptionResponse(
            detail=str(exc),
            request=request.url.path,
        ).model_dump(),
    )
