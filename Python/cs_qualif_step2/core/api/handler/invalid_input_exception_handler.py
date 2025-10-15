from fastapi import Request, status
from fastapi.responses import JSONResponse

from cs_qualif_step2.core.api.handler.response.exception_response import ExceptionResponse

async def invalid_input_exception_handler(request: Request, exc: Exception) -> JSONResponse:
    return JSONResponse(
        status_code=status.HTTP_400_BAD_REQUEST,
        content=ExceptionResponse(
            detail=str(exc),
            request=request.url.path,
        ).model_dump(),
    )
