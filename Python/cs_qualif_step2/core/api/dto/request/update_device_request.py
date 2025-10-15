from pydantic import BaseModel, validator

class DeviceUpdateRequest(BaseModel):
    firmwareVersion: str
    displayName: str = None
    location: str = None
    timezone: str = None

    @validator('firmwareVersion')
    def not_empty(cls, v):
        if v is None or v.strip() == '':
            raise ValueError('Field cannot be empty or null')
        return v
