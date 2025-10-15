from pydantic import BaseModel, validator

class DeviceRegistrationRequest(BaseModel):
    macAddress: str
    model: str
    firmwareVersion: str
    serialNumber: str
    displayName: str = None
    location: str = None
    timezone: str = None

    @validator('macAddress', 'model', 'firmwareVersion', 'serialNumber')
    def not_empty(cls, v):
        if v is None or v.strip() == '':
            raise ValueError('Field cannot be empty or null')
        return v
